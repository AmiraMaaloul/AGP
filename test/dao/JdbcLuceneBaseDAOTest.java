package dao;

import static org.junit.Assert.assertArrayEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StopWatch;

import business.AgpException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring-test.xml")
public class JdbcLuceneBaseDAOTest {

	@Autowired
	@Qualifier("jdbcLuceneBaseFirstPlanDAO")
	private BaseDAO baseFirstPlanDAO;

	@Autowired
	@Qualifier("jdbcLuceneBaseSecondPlanDAO")
	private BaseDAO baseSecondPlanDAO;

	@Before
	public void setup() {
		try {
			FileUtils.cleanDirectory(new File("/Users/amira/text"));
		} catch (IOException e) {
		}
	}

	@Test
	public void testAddText() {

		baseFirstPlanDAO.addText("1", "Hello how are you ?");

	}

	@Test
	public void testExecuteQuery() throws AgpException {

		QueryResult queryResult = baseFirstPlanDAO.executeQuery("select siteName from site");

		while (queryResult.next()) {
			try {
				String name = queryResult.getString("siteName");
				System.out.println(name);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Test
	public void testExecuteMixedQuery() throws Exception {

		baseFirstPlanDAO.addText("5", "Hello how are you ?");

		QueryResult queryResult = baseFirstPlanDAO.executeQuery("select siteId, siteName from site with hello you");

		while (queryResult.next()) {
			String name = queryResult.getString("siteName");
			System.out.println(name);
		}
	}

	@Test
	public void testComparePlans() throws Exception {
		baseFirstPlanDAO.addText("1", "Hello how are you ?");
		baseFirstPlanDAO.addText("2", "Hello how are you ?");
		baseFirstPlanDAO.addText("3", "Hello how are you ?");
		baseFirstPlanDAO.addText("4", "Hello how are you ?");
		baseFirstPlanDAO.addText("5", "Hello how are you ?");
		baseFirstPlanDAO.addText("6", "Hello how are you ?");
		baseFirstPlanDAO.addText("7", "Hello how are you ?");
		baseFirstPlanDAO.addText("8", "Hello how are you ?");
		baseFirstPlanDAO.addText("9", "Hello how are you ?");
		baseFirstPlanDAO.addText("10", "Hello how are you ?");

		QueryResult queryResult1 = baseFirstPlanDAO.executeQuery("select siteId, siteName from site with hello you");

		QueryResult queryResult2 = baseSecondPlanDAO.executeQuery("select siteId, siteName from site with hello you");

		List<String> results1 = new ArrayList<String>();
		List<String> results2 = new ArrayList<String>();
		while (queryResult1.next()) {
			results1.add(queryResult1.getString("siteId"));
			System.out.println(queryResult1.getString("siteId"));
		}
		System.out.println("----------------");
		while (queryResult2.next()) {
			results2.add(queryResult2.getString("siteId"));
			System.out.println(queryResult2.getString("siteId"));
		}

		assertArrayEquals(results1.toArray(), results2.toArray());

	}

	@Test
	public void testComparePlansPerf() throws Exception {

		for (int i = 1; i <= 10; i++) {
			baseFirstPlanDAO.addText("" + i, "Hello how are you ?");
		}

		StopWatch sw1 = new StopWatch();
		sw1.start();
		QueryResult queryResult1 = baseFirstPlanDAO.executeQuery("select siteId, siteName from site with hello you");
		sw1.stop();
		
		System.out.println("Temps plan 1: " + sw1.prettyPrint());
		
		StopWatch sw2 = new StopWatch();
		sw2.start();	
		QueryResult queryResult2 = baseSecondPlanDAO.executeQuery("select siteId, siteName from site with hello you");
		sw2.stop();
		
		System.out.println("Temps plan 2: " + sw2.prettyPrint());
	}

}
