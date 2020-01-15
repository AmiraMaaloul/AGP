package business;

public class Transport {


	private String transportId;
	private String transportType;
	private String priceKM;
	private String locationId;
	
	public Transport () {
	}
	
	
	public Transport(String transportId, String transportType, String priceKM, String locationId) {
		super();
		this.transportId = transportId;
		this.transportType = transportType;
		this.priceKM = priceKM;
		this.locationId = locationId;
	}


	public String getTransportId() {
		return transportId;
	}


	public void setTransportId(String transportId) {
		this.transportId = transportId;
	}


	public String getTransportType() {
		return transportType;
	}


	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}


	public String getPriceKM() {
		return priceKM;
	}


	public void setPriceKM(String priceKM) {
		this.priceKM = priceKM;
	}


	public String getLocationId() {
		return locationId;
	}


	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}


	@Override
	public String toString() {
		return "Transport [transportId=" + transportId + ", transportType=" + transportType + ", priceKM=" + priceKM
				+ ", locationId=" + locationId + "]";
	}
	
	
	
	
}
