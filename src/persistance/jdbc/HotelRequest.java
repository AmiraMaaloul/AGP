package persistance.jdbc;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import business.Hotel;

public class HotelRequest {
	
	public static void main(String[] args) {
		System.out.println(readAllHotel());
	}
	
	public HotelRequest() {
		
	}

	public static ArrayList<Hotel> operatorSQL(String query) {
		ArrayList<Hotel> readHotelList = new ArrayList<Hotel>();
		try {
			Connection dbConnection = JdbcConnection.getConnection();
			java.sql.PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				Hotel readHotel = new Hotel();
				readHotel.setHotelId(result.getString("hotelId"));
				readHotel.setHotelName(result.getString("hotelName"));
				readHotel.setAddress(result.getString("address"));
				readHotel.setHotelPrice(result.getString("hotelPrice"));
				readHotel.setStar(result.getString("star"));	
				readHotel.setLocationId(result.getString("locationId"));
				
				readHotelList.add(readHotel);
			}
			preparedStatement.close();

		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		return readHotelList;
	}

	private static Hotel readHotelById(int id) {
		Hotel readHotel = new Hotel();
		try {
			String selectHotelQuery = "SELECT * FROM hotel AS a WHERE a.hotelId = ? ";
			Connection dbConnection = JdbcConnection.getConnection();
			java.sql.PreparedStatement preparedStatement = dbConnection.prepareStatement(selectHotelQuery);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				readHotel.setHotelId(result.getString("hotelId"));
				readHotel.setHotelName(result.getString("hotelName"));
				readHotel.setAddress(result.getString("address"));
				readHotel.setHotelPrice(result.getString("hotelPrice"));
				readHotel.setStar(result.getString("star"));
				readHotel.setLocationId(result.getString("location"));
				
			}

			preparedStatement.close();

		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		return readHotel;
	}

	private static ArrayList<Hotel> readAllHotel() {
		ArrayList<Hotel> readHotelList = new ArrayList<Hotel>();
		try {
			String selectHotelQuery = "SELECT * FROM hotel ";
			Connection dbConnection = JdbcConnection.getConnection();
			java.sql.PreparedStatement preparedStatement = dbConnection.prepareStatement(selectHotelQuery);
			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				Hotel readHotel = new Hotel();
				readHotel.setHotelId(result.getString("hotelId"));
				readHotel.setHotelName(result.getString("hotelName"));
				readHotel.setAddress(result.getString("address"));
				readHotel.setHotelPrice(result.getString("hotelPrice"));
				readHotel.setStar(result.getString("star"));
				readHotel.setLocationId(result.getString("locationId"));
			

				readHotelList.add(readHotel);
			}
			preparedStatement.close();

		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		return readHotelList;
	}



}
