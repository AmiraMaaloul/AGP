package business;

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


	

	




	
}
