package persistance.lucene;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
public class LuceneTester {
	
   public String indexDir = new File("src").getAbsolutePath() + "\\persistance\\indexed";
   public String dataDir = new File("src").getAbsolutePath() + "\\persistance\\description";
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
            System.out.println("File: "
                    + result);
      }  
   }
}