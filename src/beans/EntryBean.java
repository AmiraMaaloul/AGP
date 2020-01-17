package beans;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import business.Hotel;
import business.Site;



/**
 * Simulation bean controller used to collect simulation entry parameters and to start the simulation.
 * 
 * The proxy design pattern is used for avoiding redundant code copy.
 */
@ManagedBean
@SessionScoped
public class EntryBean {

	/**
	 * Proxy encapsulated object. All get/set of parameters work on this proxy object.
	 */
	
	/* For Offers */
	private String offerkeyword;
	private String offerpricemin;
	private String offerpricemax;
	private String offernbday;
	private String offerconfort;

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
	
	
	private ArrayList<Site> sites ;
	private ArrayList<Hotel> hotels;
	
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
	public String getOfferconfort() {
		return offerconfort;
	}
	public void setOfferconfort(String offerconfort) {
		this.offerconfort = offerconfort;
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
	
	
	
	public ArrayList<Site> getSites() {
		return sites;
	}
	public void setSites(ArrayList<Site> sites) {
		this.sites = sites;
	}
	public ArrayList<Hotel> getHotels() {
		return hotels;
	}
	public void setHotels(ArrayList<Hotel> hotels) {
		this.hotels = hotels;
	}
	public String searchHotels() {
		
		setHotels(Hotel.getHotelsByCreteria(hotelName,hotelPriceMin, hotelPriceMax));
		return "HotelResult";
	}
	
	public String searchSites() {
			
		setSites(Site.getSitesByCreteria(siteName,sitePriceMin, sitePriceMax , siteType));
		return "SiteResult";
	}


	
	
	

}








