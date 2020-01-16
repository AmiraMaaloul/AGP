package beans;

import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class EntryBean implements Serializable {

	private static final long serialVersionUID = -426721429642192283L;

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

	public void setOfferCconfort(String offerconfort) {
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

	public String searchhotels() {

		return "HotelResult";
	}

	public String searchoffers() {

		return "OfferResult";
	}

	public String searchsites() {

		return "SiteResult";
	}

}