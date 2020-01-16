package business;

public class TestDataFactory {

	public static Hotel getHotel(String name, int price) {
		Hotel hotel = new Hotel();
		hotel.setHotelName(name);
		hotel.setHotelPrice(Integer.toString(price));
		return hotel;
	}
	
	public static Site getSite(String name, int price) {
		Site site = new Site();
		site.setSiteName(name);
		site.setSitePrice(Integer.toString(price));
		return site;
	}
	
}
