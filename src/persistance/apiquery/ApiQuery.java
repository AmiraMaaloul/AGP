package persistance.apiquery;
import java.sql.SQLException;
import java.util.ArrayList;

import business.Hotel;
import persistance.jdbc.HotelRequest;



public class ApiQuery {




public ArrayList<Site> executeQuery(String query) throws SQLException {

String simpleQuery = new String();
        String textuelQuery = new String();
        ArrayList<Object> results = new ArrayList<Object>();
       
        String[] splited = query.split("WITH");
        if((splited.length == 2)) {
        simpleQuery = splited[0];
        textuelQuery = splited[1] ;
        }else {
        simpleQuery = splited[0];
        System.out.println(simpleQuery);
        results = simpleQuery(simpleQuery);
        }
        return results ;
}



/*
* First Operator : for simple Query
*
*
*
*/

public ArrayList<Object> simpleQuery(String simpleQuery)throws SQLException {
	
	ArrayList<Object> results  =  new ArrayList<Object>();
	if(search ("Location" , simpleQuery)) {
		
	}
	if (search ("Hotel" , simpleQuery)) {
		HotelRequest hotel = new HotelRequest();
		results = (ArrayList<Object>) hotel.operatorSQL(simpleQuery);
	}
	if (search ("Site" , simpleQuery)) {	
	}
	if (search ("Transport" , simpleQuery)) {
	}
	
	
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
