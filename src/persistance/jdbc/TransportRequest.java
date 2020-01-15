package persistance.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import business.Transport;

public class TransportRequest {

	public static void main(String[] args) {
		System.out.println(operatorSQL("SELECT * FROM Transport as a"));
	}
	
	public TransportRequest() {
		
	}
	
	public static ArrayList<Transport> operatorSQL(String query) {
		ArrayList<Transport> readTransportList = new ArrayList<Transport>();
		try {
			Connection dbConnection = JdbcConnection.getConnection();
			java.sql.PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				Transport readTransport = new Transport();
				readTransport.setTransportId(result.getString("transportId"));
				readTransport.setTransportType(result.getString("transportType"));
				readTransport.setPriceKM(result.getString("priceKM"));
				
				readTransportList.add(readTransport);
			}
			preparedStatement.close();

		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		return readTransportList;
	}
}
