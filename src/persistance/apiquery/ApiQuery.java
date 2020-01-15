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

public static ArrayList<String> mixedQuery(String keywords;ArrayList<Site>) {
	simpleQuery (splited[0]);
    textuelQuery(splited[1]);
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
