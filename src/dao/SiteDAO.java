package dao;

import java.util.List;

import business.Site;

public interface SiteDAO {

	Site getSiteById(int id);

	List<Site> getAllSites();

	List<Site> findSitesByCriteria(String siteName, String minPrice, String maxPrice, String siteType);

}
