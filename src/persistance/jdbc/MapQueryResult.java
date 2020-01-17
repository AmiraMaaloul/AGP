package persistance.jdbc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import dao.QueryResult;

public class MapQueryResult implements QueryResult {

	private List<Map<String, String>> resultMapList;

	private Iterator<Map<String, String>> iterator;

	private Map<String, String> currentMap;

	public MapQueryResult() {
		resultMapList = new ArrayList<Map<String, String>>();
	}

	public MapQueryResult(int size) {
		resultMapList = new ArrayList<Map<String, String>>(size);
		for (int i = 0; i < size; i++) {
			resultMapList.add(null);
		}
	}

	public void addResult(Map<String, String> resultMap) {
		resultMapList.add(resultMap);
	}

	public void addResult(int index, Map<String, String> resultMap) {
		resultMapList.set(index, resultMap);
	}

	@Override
	public void init() {
		iterator = resultMapList.iterator();
	}

	@Override
	public boolean next() {
		if (iterator == null) {
			init();
		}

		if (!iterator.hasNext()) {
			return false;
		}

		currentMap = iterator.next();

		return true;
	}

	@Override
	public String getString(String columnLabel) {

		if (currentMap != null) {
			return currentMap.get(columnLabel);
		}

		return null;
	}

	@Override
	public Map<String, String> getAllValues() {
		return new HashMap<String, String>(currentMap);
	}

}
