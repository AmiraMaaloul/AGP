package persistance.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import business.Hotel;
import dao.HotelDAO;

public class JdbcHotelDAO implements HotelDAO {

	private final static Logger logger = Logger.getLogger(JdbcHotelDAO.class);

	@Override
	public Hotel getHotelById(int id) {

		Hotel hotel = null;
		try {
			String selectHotelQuery = "SELECT * FROM hotel AS a WHERE a.hotelId = ? ";
			Connection dbConnection = JdbcConnection.getConnection();
			java.sql.PreparedStatement preparedStatement = dbConnection.prepareStatement(selectHotelQuery);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				hotel = getHotel(result);
			}

			preparedStatement.close();

		} catch (SQLException e) {
			logger.error(e);
		}
		return hotel;
	}

	@Override
	public List<Hotel> getAllHotels() {
		List<Hotel> allHotels = new ArrayList<Hotel>();
		try {
			String selectHotelQuery = "SELECT * FROM hotel ";
			Connection dbConnection = JdbcConnection.getConnection();
			java.sql.PreparedStatement preparedStatement = dbConnection.prepareStatement(selectHotelQuery);
			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				Hotel hotel = getHotel(result);
				allHotels.add(hotel);
			}
			preparedStatement.close();

		} catch (SQLException e) {
			logger.error(e);
		}
		return allHotels;
	}

	@Override
	public List<Hotel> findHotelsByCriteria(String hotelName, String minPrice, String maxPrice, String rating) {

		StringBuilder query = new StringBuilder("SELECT * from hotel where 1=1");

		if (hotelName != "") {
			query.append(" AND hotelName = ").append(hotelName);
		}
		if (minPrice != "") {
			query.append(" AND hotelPrice >= ").append(minPrice);
		}
		if (maxPrice != "") {
			query.append(" AND hotelPrice <= ").append(maxPrice);
		}
		if (rating != "") {
			query.append(" AND star >= ").append(rating);
		}

		List<Hotel> hotels = new ArrayList<Hotel>();
		try {
			String selectHotelQuery = query.toString();
			Connection dbConnection = JdbcConnection.getConnection();
			java.sql.PreparedStatement preparedStatement = dbConnection.prepareStatement(selectHotelQuery);
			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				Hotel hotel = getHotel(result);
				hotels.add(hotel);
			}
			preparedStatement.close();

		} catch (SQLException e) {
			logger.error(e);
		}
		return hotels;

	}

	private Hotel getHotel(ResultSet result) throws SQLException {
		Hotel hotel = new Hotel();
		hotel.setHotelId(result.getString("hotelId"));
		hotel.setHotelName(result.getString("hotelName"));
		hotel.setAddress(result.getString("address"));
		hotel.setHotelPrice(result.getString("hotelPrice"));
		hotel.setStar(result.getString("star"));
		hotel.setLocationId(result.getString("locationId"));
		return hotel;
	}
}
