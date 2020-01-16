package persistance.lucene;



import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.queryparser.classic.ParseException;

public class LuceneOperator {

	public static ArrayList<String> operator(String keywords)  {
		if(keywords != null) {
			LuceneTester lucene = null;
		    
		    
		    try {
		    	lucene = new LuceneTester();
				lucene.createIndex();
				lucene.search(keywords);
				LuceneTester.deleteDirectory(lucene.getIndexDir());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//LuceneTester.deleteDirectory(lucene.getIndexDir());
	
			return lucene.getResult();
		}
		return null;
	}

	public static void main(String[] args)  {
		operator("fares");
	}
}