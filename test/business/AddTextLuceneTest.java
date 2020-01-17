package business;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dao.BaseDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring-test.xml")

public class AddTextLuceneTest {

	@Autowired
	@Qualifier("jdbcLuceneBaseFirstPlanDAO")
	BaseDAO baseDAO;

	public String indexDir = new File("src").getAbsolutePath() + File.separator + "persistance" + File.separator
			+ "indexed";
	public String dataDir = new File("src").getAbsolutePath() + File.separator + "persistance" + File.separator
			+ "description";

	
	@Before
	public void setup() {
		try {
			FileUtils.cleanDirectory(new File("/Users/amira/text"));
		} catch (IOException e) {
		}
	}
	
	@Test
	public void insertData() throws IOException {

		File dataDirFile = new File(dataDir);

		File[] files = dataDirFile.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				
				return pathname.getName().endsWith(".txt");
			}
			
		});

		for (File file : files) {
			System.out.println("Current file: " + file.getName());
			String key = file.getName().substring(0, file.getName().indexOf(".txt"));
			String contents = new String(Files.readAllBytes(file.toPath()));
			baseDAO.addText(key, contents);
		}

	}

}
