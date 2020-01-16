package test;

import java.sql.SQLException;
import java.util.ArrayList;

import business.Site;
import persistance.apiquery.ApiQuery;

public class test {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ApiQuery api = new ApiQuery();
		try {
			ArrayList<Site> result = api.executeQuerySite("Select * from site WITH abrite");
			System.out.println("Results"+result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
