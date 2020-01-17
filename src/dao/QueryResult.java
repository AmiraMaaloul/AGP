package dao;

import java.util.Map;

public interface QueryResult {

	void init();

	boolean next();

	String getString(String columnLabel);

	Map<String, String> getAllValues();
}
