package persistance.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcConnection {
	private static String host = "localhost";
	private static String base = "agp";
	private static String user = "root";
	private static String password = "mysql";
	private static String url = "jdbc:mysql://" + host + "/" + base;

	/**
	 * Singleton instance.
	 */
	private static Connection connection;

	public static Connection getConnection() {		
		if (connection == null) {
			try {
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());
				connection = DriverManager.getConnection(url, user, password);
			} catch (Exception e) {
				System.err.println("Connection failed : " + e.getMessage());			
			}
		}
		return connection;
	}
}
