package business;

public class Location {

	private String locationId;
	private String locationLatitude;
	private String locationLongitude;

	public Location(String locationId, String locationLatitude, String locationLongitude) {
		super();
		this.locationId = locationId;
		this.locationLatitude = locationLatitude;
		this.locationLongitude = locationLongitude;
	}

	public Location() {

	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getLocationLatitude() {
		return locationLatitude;
	}

	public void setLocationLatitude(String locationLatitude) {
		this.locationLatitude = locationLatitude;
	}

	public String getLocationLongitude() {
		return locationLongitude;
	}

	public void setLocationLongitude(String locationLongitude) {
		this.locationLongitude = locationLongitude;
	}

	@Override
	public String toString() {
		return "Location [locationId=" + locationId + ", locationLatitude=" + locationLatitude + ", locationLongitude="
				+ locationLongitude + "]";
	}

}
