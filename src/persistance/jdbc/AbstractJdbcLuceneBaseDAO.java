package persistance.jdbc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import business.AgpException;
import dao.BaseDAO;
import dao.QueryResult;
import indexer.TextIndexer;
import searcher.TextSearcher;

public abstract class AbstractJdbcLuceneBaseDAO implements BaseDAO {

	private final static Logger logger = Logger.getLogger(AbstractJdbcLuceneBaseDAO.class);

	private final static String QUERY_SEPARATOR = " WITH ";

	private String tableName;

	protected String keyName;

	private String textFolderPath;

	private TextIndexer textIndexer;

	protected TextSearcher textSearcher;

	public AbstractJdbcLuceneBaseDAO() {
	}

	@Override
	public void addText(String key, String text) {

		File textFile = new File(textFolderPath, key);
		
		// Si le fichier existe déjà on ne l'ajoute pas.
		if (textFile.exists()) {
			return;
		}

		BufferedWriter writer = null;

		try {
			writer = new BufferedWriter(new FileWriter(textFile));

			writer.write(text);

		} catch (IOException e) {
			logger.error(e);
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				logger.error(e);
			}
		}

		textIndexer.index(textFile);
	}

	@Override
	public QueryResult executeQuery(String query) throws AgpException {

		int index = query.toUpperCase().indexOf(QUERY_SEPARATOR);
		if (index != -1) {
			String sqlQuery = query.substring(0, index);
			String textQuery = query.substring(index + QUERY_SEPARATOR.length());
			return executeMixedQuery(sqlQuery, textQuery);
		} else {
			return executeSqlQuery(query);
		}
	}

	protected QueryResult executeSqlQuery(String sqlQuery) {
		MapQueryResult mapQueryResult = new MapQueryResult();

		Connection dbConnection = JdbcConnection.getConnection();
		Statement statement = null;
		try {
			statement = dbConnection.createStatement();
			ResultSet rs = statement.executeQuery(sqlQuery);

			ResultSetMetaData metaData = rs.getMetaData();

			int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				Map<String, String> resultMap = new HashMap<String, String>();

				for (int i = 1; i < columnCount + 1; i++) {

					String columnName = metaData.getColumnName(i);
					String columnValue = rs.getString(i);

					resultMap.put(columnName, columnValue);
				}
				mapQueryResult.addResult(resultMap);
			}

		} catch (SQLException e) {
			logger.error(e);
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}
		}

		return mapQueryResult;
	}

	protected abstract QueryResult executeMixedQuery(String sqlQuery, String textQuery) throws AgpException;

	/**
	 * @param sqlQuery
	 * @param textQuery
	 * @return
	 * @throws AgpException
	 */

	@Required
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Required
	public void setTextFolderPath(String textFolderPath) {
		this.textFolderPath = textFolderPath;
	}

	@Required
	public void setTextIndexer(TextIndexer textIndexer) {
		this.textIndexer = textIndexer;
	}

	@Required
	public void setTextSearcher(TextSearcher textSearcher) {
		this.textSearcher = textSearcher;
	}

	@Required
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

}
