package dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import business.Site;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring-test.xml")
public class JdbcSiteDAOTest {

	@Autowired
	private SiteDAO siteDAO;

	@Test
	public void testGetSiteById() {
		Site site = siteDAO.getSiteById(1);

		assertEquals("1", site.getSiteId());
	}

	@Test
	public void testGetAllSites() {
		List<Site> Sites = siteDAO.getAllSites();

		assertEquals(101, Sites.size());
	}

	@Test
	public void testFindSitesByCriteria() {

		List<Site> sites = siteDAO.findSitesByCriteria("", "", "50", "");
		assertEquals(77, sites.size());
	}

}
