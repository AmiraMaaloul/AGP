package persistance.apiquery;
import java.sql.SQLException;
import java.util.ArrayList;



import business.Hotel;
import business.Location;
import business.Site;
import business.Transport;
import persistance.jdbc.HotelRequest;
import persistance.jdbc.LocationRequest;
import persistance.jdbc.TransportRequest;
import persistance.jdbc.SiteRequest;
import persistance.lucene.LuceneOperator;



public class ApiQuery {
	
	public static void main(String[] args) throws SQLException {
	
		
		//String query = "SELECT * FROM site AS a with fares  ";
		//ArrayList<Site>  readHotelList = executeQuerySite(query);
		//System.out.println(readHotelList);
	}


	public static ArrayList<Site> executeQuerySite(String query) throws SQLException {
	
	     	ArrayList<Site> results = new ArrayList<Site>();
	     	
	        String[] splited = query.split("WITH");
	        if((splited.length == 2)) {
	        	results = mixedQuery(query);
	        }else {
	        	results  = simpleQuerySite(splited[0]);
	        }
	        return results ;
	}

	public static ArrayList<Site> simpleQuerySite(String querySQL) {
		return SiteRequest.operatorSQL(querySQL);
	}

	public static ArrayList<Hotel> simpleQueryHotel(String querySQL) {
		return HotelRequest.operatorSQL(querySQL);
	}

	//public static ArrayList<Location> simpleQueryLocation(String querySQL) {
		//return LocationRequest.operatorSQL(querySQL);
	//}

	//public static ArrayList<Transport> simpleQueryTransport(String querySQL) {
		//return TransportRequest.operatorSQL(querySQL);
	//}

	public static ArrayList<String> textuelQuery(String keywords) {
		return LuceneOperator.operator(keywords);
	}

	public static ArrayList<Site> mixedQuery(String query) {
		
		ArrayList<String> luceneResults = new ArrayList<String>();
		ArrayList<Site>  sqlResults = new ArrayList<Site>();
		ArrayList<String>  list = new ArrayList<String>();
		ArrayList<Site>  mixedResults = new ArrayList<Site>();
		
		String[] splitedQuery = query.split("WITH");
		
		sqlResults = simpleQuerySite(splitedQuery[0]);
		luceneResults = textuelQuery(splitedQuery[1]);
	    
	    for (Site site : sqlResults) {
			String id = site.getSiteId();
			for (String fileName : luceneResults){
				String[] fileId = fileName.split(".txt");
				if((fileId[0]).equals(id)) {
					list.add(fileName);
				}
			}
		}
	    
	    /* Sort returned By the lucene */
	    for (int i = (list.size() - 1); i >= 0; i--) {
			for (int j = 1; j <= i; j++) {
				String[] partj1 = list.get(j - 1).split("/");
				double scorej1 = Double.valueOf(partj1[1]);
				String[] partj = list.get(j).split("/");
				double scorej = Double.valueOf(partj[1]);
				if (scorej1 < scorej) {
					String temp = list.get(j-1);
					list.set(j-1, list.get(j));
					list.set(j,temp);
				}
			}
		}
	    /* Convert to an array of sites */
	    for (String file : list) {
			String[] element = file.split(".txt");
			int id = Integer.valueOf(element[0]);
			Site site = SiteRequest.readSiteById(id);
			mixedResults.add(site);
		}
	    return mixedResults;
	}




}
