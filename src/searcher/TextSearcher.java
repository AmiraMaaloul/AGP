package searcher;

import java.util.List;

import business.AgpException;

public interface TextSearcher {

	List<String> findTextKeys(String searchQuery) throws AgpException;
}
