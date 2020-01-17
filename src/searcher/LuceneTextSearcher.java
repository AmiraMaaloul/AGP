package searcher;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import business.AgpException;
import indexer.LuceneConstants;

public class LuceneTextSearcher implements TextSearcher {

	private final static Logger logger = Logger.getLogger(LuceneTextSearcher.class);

	private String indexPath;

	public LuceneTextSearcher(String indexPath) {
		this.indexPath = indexPath;
	}

	@Override
	public List<String> findTextKeys(String searchQuery) throws AgpException {

		List<String> result = new ArrayList<String>();

		try {

			Directory indexDirectory = FSDirectory.open(Paths.get(indexPath));

			IndexSearcher indexSearcher = new IndexSearcher(DirectoryReader.open(indexDirectory));

			QueryParser queryParser = new QueryParser(LuceneConstants.CONTENTS, new StandardAnalyzer());

			Query query = queryParser.parse(searchQuery);
			TopDocs topDocs = indexSearcher.search(query, LuceneConstants.MAX_SEARCH);

			for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
				Document document = indexSearcher.doc(scoreDoc.doc);
				result.add(document.getField(LuceneConstants.KEY).stringValue());
			}

		} catch (ParseException | IOException e) {
			logger.error(e);
			throw new AgpException(e);
		}

		return result;
	}

}
