package persistance.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import business.Site;
import dao.SiteDAO;

public class JdbcSiteDAO implements SiteDAO {

	private final static Logger logger = Logger.getLogger(JdbcSiteDAO.class);

	@Override
	public Site getSiteById(int id) {
		Site site = null;
		try {
			String selectSiteQuery = "SELECT * FROM site  WHERE siteId = ? ";
			Connection dbConnection = JdbcConnection.getConnection();
			java.sql.PreparedStatement preparedStatement = dbConnection.prepareStatement(selectSiteQuery);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				site = getSite(result);
			}

			preparedStatement.close();

		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		return site;
	}

	@Override
	public List<Site> getAllSites() {

		ArrayList<Site> allSites = new ArrayList<Site>();
		try {
			String selectSiteQuery = "SELECT * FROM site ";
			Connection dbConnection = JdbcConnection.getConnection();
			java.sql.PreparedStatement preparedStatement = dbConnection.prepareStatement(selectSiteQuery);
			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				Site site = getSite(result);

				allSites.add(site);
			}
			preparedStatement.close();

		} catch (SQLException e) {
			logger.error(e);
		}
		return allSites;
	}

	@Override
	public List<Site> findSitesByCriteria(String siteName, String minPrice, String maxPrice, String siteType) {
		StringBuilder query = new StringBuilder("SELECT * from site where 1=1");

		if (siteName != "") {
			query.append(" AND siteName = ").append(siteName);
		}
		if (minPrice != "") {
			query.append(" AND sitePrice >= ").append(minPrice);
		}
		if (maxPrice != "") {
			query.append(" AND sitePrice <= ").append(maxPrice);
		}
		if (siteType != "") {
			query.append(" AND siteType = ").append(siteType);
		}

		List<Site> sites = new ArrayList<Site>();
		try {
			String selectSiteQuery = query.toString();
			Connection dbConnection = JdbcConnection.getConnection();
			java.sql.PreparedStatement preparedStatement = dbConnection.prepareStatement(selectSiteQuery);
			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				Site site = getSite(result);
				sites.add(site);
			}
			preparedStatement.close();

		} catch (SQLException e) {
			logger.error(e);
		}
		return sites;

	}

	private Site getSite(ResultSet result) throws SQLException {
		Site site = new Site();
		site.setSiteId(result.getString("siteId"));
		site.setSiteName(result.getString("siteName"));
		site.setSiteType(result.getString("siteType"));
		site.setSitePrice(result.getString("sitePrice"));
		site.setLocationId(result.getString("locationId"));

		return site;
	}
}
