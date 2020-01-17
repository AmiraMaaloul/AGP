package persistance.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class JdbcConnection {

	private final static Logger logger = Logger.getLogger(JdbcConnection.class);

	private static String host = "localhost";
	private static String base = "agp";
	private static String user = "root";
	private static String password = "mysql";
	private static String url = "jdbc:mysql://" + host + ":3306/" + base;

	/**
	 * Singleton instance.
	 */
	private static Connection connection;

	public static Connection getConnection() {
		try {
			if (connection == null || connection.isClosed()) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					connection = DriverManager.getConnection(url, user, password);
				} catch (Exception e) {
					logger.error("Connection failed : " + e.getMessage());
				}
			}
		} catch (SQLException e) {
			logger.error(e);
		}
		return connection;
	}
}
