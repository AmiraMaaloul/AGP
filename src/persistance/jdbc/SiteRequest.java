package persistance.jdbc;
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

import business.*;

public class SiteRequest {
	
	public static void main(String[] args) {
		System.out.println(operatorSQL("select * FROM site"));
	}
	
	public SiteRequest() {
		
	}
	public static ArrayList<Site> operatorSQL(String query) {
		ArrayList<Site> readSiteList = new ArrayList<Site>();
		try {
			Connection dbConnection = JdbcConnection.getConnection();
			java.sql.PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				Site readSite = new Site();
				readSite.setSiteId(result.getString("siteId"));
				readSite.setSiteName(result.getString("siteName"));
				readSite.setSiteType(result.getString("siteType"));
				readSite.setSitePrice(result.getString("sitePrice"));
				readSite.setLocationId(result.getString("locationId"));
				readSiteList.add(readSite);
			}
			preparedStatement.close();

		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		return readSiteList;
	}

	public static Site getSiteById(int id) {
		Site readSite = new Site();
		try {
			String selectSiteQuery = "SELECT * FROM site  WHERE siteId = ? ";
			Connection dbConnection = JdbcConnection.getConnection();
			java.sql.PreparedStatement preparedStatement = dbConnection.prepareStatement(selectSiteQuery);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				readSite.setSiteId(result.getString("siteId"));
				readSite.setSiteName(result.getString("siteName"));
				readSite.setSiteType(result.getString("siteType"));
				readSite.setSitePrice(result.getString("sitePrice"));
				readSite.setLocationId(result.getString("locationId"));
			}

			preparedStatement.close();

		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		return readSite;
	}

	private static ArrayList<Site> readAllSite() {
		
		System.out.println("okokokokok");
		ArrayList<Site> readSiteList = new ArrayList<Site>();
		try {
			String selectSiteQuery = "SELECT * FROM site ";
			Connection dbConnection = JdbcConnection.getConnection();
			java.sql.PreparedStatement preparedStatement = dbConnection.prepareStatement(selectSiteQuery);
			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				Site readSite = new Site();
				readSite.setSiteId(result.getString("siteId"));
				readSite.setSiteName(result.getString("siteName"));
				readSite.setSiteType(result.getString("siteType"));
				readSite.setSitePrice(result.getString("sitePrice"));
				readSite.setLocationId(result.getString("locationId"));

				readSiteList.add(readSite);
			}
			preparedStatement.close();

		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		return readSiteList;
	}

}
