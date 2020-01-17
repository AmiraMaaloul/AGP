package persistance.jdbc;

import java.util.List;
import java.util.Map;

import business.AgpException;
import dao.QueryResult;

public class JdbcLuceneBaseSecondPlanDAO extends AbstractJdbcLuceneBaseDAO {

	protected QueryResult executeMixedQuery(String sqlQuery, String textQuery) throws AgpException {

		List<String> textKeys = textSearcher.findTextKeys(textQuery);

		MapQueryResult mapQueryResult = new MapQueryResult(textKeys.size());

		int index = 0;
		for (String key : textKeys) {
			String newQuery = QueryUtils.addClause(sqlQuery, keyName + "=" + key);
			QueryResult sqlQueryResult = executeSqlQuery(newQuery);
			if (sqlQueryResult.next()) {
				Map<String, String> allValues = sqlQueryResult.getAllValues();

				mapQueryResult.addResult(index, allValues);
			}

			index++;
		}

		return mapQueryResult;
	}

}
