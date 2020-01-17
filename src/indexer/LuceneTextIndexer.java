package indexer;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Required;

public class LuceneTextIndexer implements TextIndexer {

	private String indexPath;

	@Override
	public void index(File textFile) {

		StandardAnalyzer analyzer = new StandardAnalyzer();

		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);

		try {
			Directory indexDirectory = FSDirectory.open(Paths.get(indexPath));
			IndexWriter indexWriter = new IndexWriter(indexDirectory, indexWriterConfig);
			Document document = new Document();

			FileReader fileReader = new FileReader(textFile);
			document.add(new TextField(LuceneConstants.CONTENTS, fileReader));
			document.add(new TextField(LuceneConstants.KEY, textFile.getName(), Store.YES));
			indexWriter.addDocument(document);
			indexWriter.close();
		} catch (IOException e) {
		}
	}

	@Required
	public void setIndexPath(String indexPath) {
		this.indexPath = indexPath;
	}

}
