package persistance.jdbc;

import java.util.List;
import java.util.Map;

import business.AgpException;
import dao.QueryResult;

public class JdbcLuceneBaseFirstPlanDAO extends AbstractJdbcLuceneBaseDAO {

	protected QueryResult executeMixedQuery(String sqlQuery, String textQuery) throws AgpException {

		QueryResult sqlQueryResult = executeSqlQuery(sqlQuery);

		List<String> textKeys = textSearcher.findTextKeys(textQuery);

		MapQueryResult mapQueryResult = new MapQueryResult(textKeys.size());

		while (sqlQueryResult.next()) {
			Map<String, String> allValues = sqlQueryResult.getAllValues();

			String key = allValues.get(keyName);
			int index = textKeys.indexOf(key);
			// Si on trouve la clé dans le résultat de la recherche textuelle, on met le
			// résultat de la requête SQL au même endroit dans la liste pour garder l'ordre.

			if (index != -1) {
				mapQueryResult.addResult(index, allValues);
			}
		}

		return mapQueryResult;
	}

}
