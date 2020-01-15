/**
 * 
 */
package persistance.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import business.Location;
/**
 * @author fares
 *
 */
public class LocationRequest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Location location = new Location("56", "999", "999");
		//persistLocation(location);

		//Location locationWithId = readLocation(location);
		//System.out.print(locationWithId);
		
		

	}
	
	
	private static void persistLocation(Location location) {
		try {

			String insertLocationQuery = "INSERT INTO location (locationId,locationLatitude,locationLongitude) VALUES (?,?,?)";

			 
			
			Connection dbConnection = JdbcConnection.getConnection();
			PreparedStatement preparedStatement = dbConnection.prepareStatement(insertLocationQuery);

			//Set values of parameters in the query.
			preparedStatement.setString(1, location.getLocationId());
			preparedStatement.setString(2, location.getLocationLatitude());
			preparedStatement.setString(3, location.getLocationLongitude());
		

			preparedStatement.executeUpdate();

			preparedStatement.close();
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
	}
	
	private static Location readLocation(Location location) {
		Location readLocation = new Location();
		try {

			String selectAddressQuery = "SELECT * FROM location AS a WHERE a.locationId = ? AND a.locationLatitude = ? AND a.locationLongitude = ?";

			Connection dbConnection = JdbcConnection.getConnection();
			PreparedStatement preparedStatement = dbConnection.prepareStatement(selectAddressQuery);

			preparedStatement.setString(1, location.getLocationId() );
			preparedStatement.setString(2, location.getLocationLatitude());
			preparedStatement.setString(3, location.getLocationLongitude());

			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				readLocation.setLocationId(result.getString("locationId"));
				readLocation.setLocationLatitude(result.getString("locationLatitude"));
				readLocation.setLocationLongitude(result.getString("locationLongitude"));
		
			}

			preparedStatement.close();

		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		return readLocation;
	}

}
