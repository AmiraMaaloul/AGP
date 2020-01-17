package business;

import java.util.ArrayList;

import persistance.jdbc.HotelRequest;
import persistance.jdbc.SiteRequest;

public class Site extends Location {

	private String siteId;
	private String siteName;
	private String siteType;
	private String sitePrice;
	private String locationId;

	public Site() {

	}

	public Site(String locationId, String locationLatitude, String locationLongitude, String siteId, String siteName,
			String siteType, String sitePrice, String locationId2) {
		super(locationId, locationLatitude, locationLongitude);
		this.siteId = siteId;
		this.siteName = siteName;
		this.siteType = siteType;
		this.sitePrice = sitePrice;
		locationId = locationId2;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSiteType() {
		return siteType;
	}

	public void setSiteType(String siteType) {
		this.siteType = siteType;
	}

	public String getSitePrice() {
		return sitePrice;
	}

	public void setSitePrice(String sitePrice) {
		this.sitePrice = sitePrice;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	@Override
	public String toString() {
		return "Site [siteId=" + siteId + ", siteName=" + siteName + ", siteType=" + siteType + ", sitePrice="
				+ sitePrice + ", locationId=" + locationId + "]";
	}
	
	public static ArrayList<Site> getSitesByCreteria(String nameHotel, String pricemin , String pricemax, String siteType){
		ArrayList<Site> sites = new ArrayList<Site>();
		
		String query = "SELECT * from site where 1=1";
		if(nameHotel != "") {
			query += " AND siteName = "+nameHotel;
		}
		if(pricemin != "") {
			query += " AND sitePrice >= "+pricemin;
		}
		if(pricemax != "") {
			query += " AND sitePrice >= "+pricemax;
		}
		if(siteType != "") {
			query += " AND siteType = "+siteType;
		}
		
		sites =SiteRequest.operatorSQL(query);
		return sites;
	}

	
	

}
