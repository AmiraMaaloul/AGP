package dao;

import business.AgpException;

public interface BaseDAO {

	void addText(String key, String text);

	QueryResult executeQuery(String sqlQuery) throws AgpException;

}
