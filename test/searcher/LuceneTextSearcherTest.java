package searcher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import business.AgpException;
import dao.BaseDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring-test.xml")
public class LuceneTextSearcherTest {

	@Autowired
	@Qualifier("jdbcLuceneBaseFirstPlanDAO")
	private BaseDAO baseDAO;

	@Autowired
	private TextSearcher textSearcher;

	@Before
	public void setup() {
		try {
			FileUtils.cleanDirectory(new File("/Users/amira/text"));
		} catch (IOException e) {
		}
	}

	@Test
	public void testFindTextKeys() {
		baseDAO.addText("1.txt", "Hello how are you ?");
		baseDAO.addText("2.txt", "Hello Amira");
		baseDAO.addText("3.txt", "Hello guys");

		try {
			List<String> keys = textSearcher.findTextKeys("Hello");

			for (String key : keys) {
				System.out.println(key);
			}

			assertEquals(3, keys.size());
			
		} catch (AgpException e) {
			fail(e.getMessage());
		}
	}

}
