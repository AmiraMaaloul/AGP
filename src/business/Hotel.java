package business;

import java.util.ArrayList;

import persistance.jdbc.HotelRequest;

public class Hotel extends Location  {

	private String hotelId;
	private String hotelName;
	private String hotelPrice;
	private String address;
	private String star;
	private String locationId;
	
	public Hotel() {
		
	}
	
	public Hotel(String locationId, String locationLatitude, String locationLongitude, String hotelId, String hotelName,
			String hotelPrice, String address, String star, String locationId2) {
		super(locationId, locationLatitude, locationLongitude);
		this.hotelId = hotelId;
		this.hotelName = hotelName;
		this.hotelPrice = hotelPrice;
		this.address = address;
		this.star = star;
		locationId = locationId2;
	}
	


	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getHotelPrice() {
		return hotelPrice;
	}

	public void setHotelPrice(String hotelPrice) {
		this.hotelPrice = hotelPrice;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	@Override
	public String toString() {
		return "Hotel [hotelId=" + hotelId + ", hotelName=" + hotelName + ", hotelPrice=" + hotelPrice + ", address="
				+ address + ", star=" + star + ", locationId=" + locationId + "]";
	}
	
	
	public static ArrayList<Hotel> getHotelsByCreteria(String nameHotel, String pricemin , String pricemax){
		ArrayList<Hotel> hotels = new ArrayList<Hotel>();
		
		String query = "SELECT * from hotel where 1=1";
		if(nameHotel != "") {
			query += " AND hotelName = "+nameHotel;
		}
		if(pricemin != "") {
			query += " AND hotelPrice >= "+pricemin;
		}
		if(pricemax != "") {
			query += " AND hotelPrice <= "+pricemax;
		}
		
		
		hotels = HotelRequest.operatorSQL(query);
		return hotels;
	}
	
	
	

	
	
	
}
