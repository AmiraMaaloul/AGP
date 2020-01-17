package persistance.jdbc;

public class QueryUtils {

	private QueryUtils() {

	}

	public static String addClause(String query, String clause) {

		String newQuery = query.toUpperCase();

		if (newQuery.contains("WHERE")) {
			return newQuery.replace("WHERE", "WHERE " + clause + " and ");
		} else {
			return newQuery + " WHERE " + clause;
		}
	}

}
