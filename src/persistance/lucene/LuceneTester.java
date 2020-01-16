package persistance.lucene;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
public class LuceneTester {
	
   public String indexDir = new File("src").getAbsolutePath() + File.separator + "persistance" + File.separator + "indexed";
   public String dataDir = new File("src").getAbsolutePath() + File.separator + "persistance" + File.separator + "description";
   public ArrayList<String> result = new ArrayList<String>();
   Indexer indexer;
   Searcher searcher;
   public static void main(String[] args) {
      LuceneTester tester;
      try {
         tester = new LuceneTester();
         tester.createIndex();
         tester.search("bali");
      } catch (IOException e) {
         e.printStackTrace();
      } catch (ParseException e) {
         e.printStackTrace();
      }
   }
   
   public ArrayList<String> getResult() {
	return result;
}

public void setResult(ArrayList<String> result) {
	this.result = result;
}



public String getIndexDir() {
	return indexDir;
}

public void setIndexDir(String indexDir) {
	this.indexDir = indexDir;
}

public String getDataDir() {
	return dataDir;
}

public void setDataDir(String dataDir) {
	this.dataDir = dataDir;
}

public Indexer getIndexer() {
	return indexer;
}

public void setIndexer(Indexer indexer) {
	this.indexer = indexer;
}

public Searcher getSearcher() {
	return searcher;
}

public void setSearcher(Searcher searcher) {
	this.searcher = searcher;
}

public void createIndex() throws IOException {
      indexer = new Indexer(indexDir);
      int numIndexed;
      long startTime = System.currentTimeMillis();	
      numIndexed = indexer.createIndex(dataDir, new TextFileFilter());
      long endTime = System.currentTimeMillis();
      indexer.close();
      System.out.println(numIndexed+" File indexed, time taken: "
         +(endTime-startTime)+" ms");		
   }
   public void search(String searchQuery) throws IOException, ParseException {
      searcher = new Searcher(indexDir);
      long startTime = System.currentTimeMillis();
      TopDocs hits = searcher.search(searchQuery);
      long endTime = System.currentTimeMillis();
   
      System.out.println(hits.totalHits +
         " documents found. Time :" + (endTime - startTime));
      for(ScoreDoc scoreDoc : hits.scoreDocs) {
         Document doc = searcher.getDocument(scoreDoc);
          //  System.out.println("File: "
          //  + doc.get(LuceneConstants.FILE_PATH));
            String resultSearsh = doc.get(LuceneConstants.FILE_PATH);
            result.add(resultSearsh);
            /*System.out.println("File: "
                    + result);*/
      }  
   }
   public static void deleteDirectory(String emplacement) {

		File path = new File(emplacement);
		if (path.exists()) {
			File[] files = path.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					deleteDirectory(path + File.separator + files[i]);
				} else {
					files[i].delete();
				}
			}
		}
	}
}


