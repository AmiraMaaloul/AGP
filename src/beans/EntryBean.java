package beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;

import business.AgpException;
import business.Hotel;
import business.Offer;
import business.OffersGenerator;
import business.Site;
import dao.BaseDAO;
import dao.HotelDAO;
import dao.QueryResult;
import dao.SiteDAO;
import spring.SpringIoC;

@ManagedBean
@SessionScoped
public class EntryBean {

	private final static Logger logger = Logger.getLogger(EntryBean.class);

	private HotelDAO hotelDAO = (HotelDAO) SpringIoC.getBean("hotelDAO");

	private SiteDAO siteDAO = (SiteDAO) SpringIoC.getBean("siteDAO");

	private BaseDAO baseDAO = (BaseDAO) SpringIoC.getBean("baseDAO");

	/* For Offers */
	private String offerkeyword;
	private String offerpricemin;
	private String offerpricemax;
	private String offernbday;
	private String offerHotelRating;

	/* For Hotels */
	private String hotelPriceMax;
	private String hotelPriceMin;
	private String hotelName;
	private String rating;

	/* For Sites */
	private String siteName;
	private String sitePriceMin;
	private String sitePriceMax;
	private String siteType;

	private List<Site> sites;
	private List<Hotel> hotels;
	private List<Offer> offers;

	public EntryBean() {
	}

	public String getOfferkeyword() {
		return offerkeyword;
	}

	public void setOfferkeyword(String offerkeyword) {
		this.offerkeyword = offerkeyword;
	}

	public String getOfferpricemin() {
		return offerpricemin;
	}

	public void setOfferpricemin(String offerpricemin) {
		this.offerpricemin = offerpricemin;
	}

	public String getOfferpricemax() {
		return offerpricemax;
	}

	public void setOfferpricemax(String offerpricemax) {
		this.offerpricemax = offerpricemax;
	}

	public String getOffernbday() {
		return offernbday;
	}

	public void setOffernbday(String offernbday) {
		this.offernbday = offernbday;
	}

	public String getOfferHotelRating() {
		return offerHotelRating;
	}

	public void setOfferHotelRating(String offerHotelRating) {
		this.offerHotelRating = offerHotelRating;
	}

	public String getHotelPriceMax() {
		return hotelPriceMax;
	}

	public void setHotelPriceMax(String hotelPriceMax) {
		this.hotelPriceMax = hotelPriceMax;
	}

	public String getHotelPriceMin() {
		return hotelPriceMin;
	}

	public void setHotelPriceMin(String hotelPriceMin) {
		this.hotelPriceMin = hotelPriceMin;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSitePriceMin() {
		return sitePriceMin;
	}

	public void setSitePriceMin(String sitePriceMin) {
		this.sitePriceMin = sitePriceMin;
	}

	public String getSitePriceMax() {
		return sitePriceMax;
	}

	public void setSitePriceMax(String sitePriceMax) {
		this.sitePriceMax = sitePriceMax;
	}

	public String getSiteType() {
		return siteType;
	}

	public void setSiteType(String siteType) {
		this.siteType = siteType;
	}

	public List<Site> getSites() {
		return sites;
	}

	public void setSites(ArrayList<Site> sites) {
		this.sites = sites;
	}

	public List<Hotel> getHotels() {
		return hotels;
	}

	public void setHotels(ArrayList<Hotel> hotels) {
		this.hotels = hotels;
	}

	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}

	public String searchHotels() {

		hotels = hotelDAO.findHotelsByCriteria(hotelName, hotelPriceMin, hotelPriceMax, rating);
		return "HotelResult";
	}

	public String searchSites() {

		sites = siteDAO.findSitesByCriteria(siteName, sitePriceMin, sitePriceMax, siteType);
		return "SiteResult";
	}

	public String generateOffers() {

		List<Hotel> candidateHotels = hotelDAO.findHotelsByCriteria(hotelName, "", "", offerHotelRating);
		logger.info("Nb hotels: " + candidateHotels.size());

		try {
			String query = "SELECT siteId, siteName, siteType, sitePrice, locationId from site ";
			if (!"".equals(offerkeyword.trim())) {
				query = query + "WITH " + offerkeyword;
			}
			QueryResult queryResult = baseDAO.executeQuery(query);

			List<Site> candidateSites = new ArrayList<Site>();

			while (queryResult.next()) {
				Site site = new Site();
				site.setSiteId(queryResult.getString("siteId"));
				site.setSiteName(queryResult.getString("siteName"));
				site.setSitePrice(queryResult.getString("sitePrice"));
				site.setSiteType(queryResult.getString("siteType"));

				candidateSites.add(site);
			}

			logger.info("Nb sites: " + candidateSites.size());

			int minPrice = 0;
			try {
				minPrice = Integer.valueOf(offerpricemin);
			} catch (NumberFormatException e) {
				// Ignored
			}

			int maxPrice = Integer.MAX_VALUE;
			try {
				maxPrice = Integer.valueOf(offerpricemax);
			} catch (NumberFormatException e) {
				// Ignored
			}

			int nbDays = 5; // Default value
			try {
				nbDays = Integer.valueOf(offernbday);
			} catch (NumberFormatException e) {
				// Ignored
			}

			int nbExcursions = nbDays; // Default

			int nbSites = 2; // Default

			if (!candidateSites.isEmpty() && !candidateHotels.isEmpty()) {
				OffersGenerator generator = new OffersGenerator(candidateHotels, candidateSites, minPrice, maxPrice,
						nbDays, nbExcursions, nbSites);
				offers = generator.generateOffers();
			} else {
				offers = new ArrayList<Offer>();
			}

			logger.info("Nb offers found: " + offers.size());
		} catch (AgpException e) {
			logger.error(e);
		}

		return "OfferResult";
	}

}
