package persistance.apiquery;
import java.sql.SQLException;
import java.util.ArrayList;

import business.Hotel;
import business.Site;
import persistance.jdbc.HotelRequest;
import persistance.jdbc.SiteRequest;
import persistance.lucene.LuceneOperator;



public class ApiQuery {




public ArrayList<Site> executeQuery(String query) throws SQLException {

     	ArrayList<Site> results = new ArrayList<Site>();
     	
        String[] splited = query.split("WITH");
        if((splited.length == 2)) {
        	results = mixedQuery(query);
        }else {
        	results  = simpleQuery(splited[0]);
        }
        return results ;
}



/*
* First Operator : for simple Query
*
*
*
*/

public static ArrayList<Site> simpleQuery(String querySQL) {
	return SiteRequest.operatorSQL(querySQL);
}

public static ArrayList<String> textuelQuery(String keywords) {
	return LuceneOperator.operator(keywords);
}

public static ArrayList<Site> mixedQuery(String query) {
	
	ArrayList<String> luceneResults = new ArrayList<String>();
	ArrayList<Site>  sqlResults = new ArrayList<Site>();
	ArrayList<String>  mixedResults = new ArrayList<Site>();
	
	String[] splitedQuery = query.split("WITH");
	
	sqlResults = simpleQuery (splitedQuery[0]);
	luceneResults = textuelQuery(splitedQuery[1]);
    
    for (Site site : sqlResults) {
    	
		String id = site.getSiteId();
		
		for (String fileName : luceneResults){
			String[] fileId = fileName.split(".txt");
			if((fileId[0]).equals(id)) {
				mixedResults.add(fileName);
			}
		}
	}  
    
    
    return mixedResults;
}




public static boolean search(String word , String query) {
boolean result ;

int position = query.indexOf(word);
if(position <0) {
result = false;
}else{
result = true ;
}
return result ;
}
/*
*
* Second Operator : For mixed Query
*
*
*/


public void mixedQuery() {

}



}
