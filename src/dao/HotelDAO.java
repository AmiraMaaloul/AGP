package dao;

import java.util.List;

import business.Hotel;

public interface HotelDAO {

	Hotel getHotelById(int id);

	List<Hotel> getAllHotels();

	List<Hotel> findHotelsByCriteria(String hotelName, String minPrice, String maxPrice, String rating);

}
