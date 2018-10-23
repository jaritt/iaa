package de.nordakademie.iaa.library.service.internal;

import de.nordakademie.iaa.library.model.Publication;
import de.nordakademie.iaa.library.service.SearchFailedException;
import de.nordakademie.iaa.library.service.internal.api.PublicationSearchService;
import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.MMapDirectory;

import javax.inject.Named;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Felix Welter
 */
@Named
public class PublicationSearchServiceImpl implements PublicationSearchService {

    private static final String[] AVAILABLE_FIELDS = new String[]{"key", "title", "author", "publisher", "isbn", "keywords"};

    @Override
    public List<Long> search(String searchPhrase) {
        return search(searchPhrase, AVAILABLE_FIELDS);
    }

    @Override
    public List<Long> search(String searchPhrase, String[] fields) {

        try {
            searchPhrase = QueryParser.escape(searchPhrase);

            Directory directory = new MMapDirectory(Paths.get("./db/search_index"));
            Analyzer analyzer = new StandardAnalyzer();

            DirectoryReader directoryReader = DirectoryReader.open(directory);
            IndexSearcher indexSearcher = new IndexSearcher(directoryReader);

            MultiFieldQueryParser parser = new MultiFieldQueryParser(fields, analyzer);

            String searchString = searchPhrase.trim().replace(" ", "~ ") + "~";

            Query query = parser.parse(searchString);

            ScoreDoc[] hits = indexSearcher.search(query, 100).scoreDocs;

            if (hits.length == 0) {
                query = parser.parse(searchPhrase.trim().replace(" ", "* ") + "*");
                hits = indexSearcher.search(query, 100).scoreDocs;
            }

            List<Long> matchedPublicationIds = new ArrayList<>();

            for (ScoreDoc hit : hits) {
                Document hitDoc = indexSearcher.doc(hit.doc);

                matchedPublicationIds.add(Long.parseLong(hitDoc.get("id")));
            }

            directoryReader.close();
            directory.close();

            return matchedPublicationIds;

        } catch (IOException | ParseException e2) {
            throw new SearchFailedException();
        }

    }

    @Override
    public void rebuildIndex(List<Publication> publications) {
        // Inspired from: http://lucene.apache.org/core/7_5_0/core/overview-summary.html#overview.description
        try {

            FileUtils.deleteDirectory(new File("./db/search_index"));

            Analyzer analyzer = new StandardAnalyzer();

            Directory directory = new MMapDirectory(Paths.get("./db/search_index"));

            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            IndexWriter indexWriter = new IndexWriter(directory, config);

            for (Publication publication :
                    publications) {
                Document doc = new Document();
                doc.add(new Field("id", publication.getId().toString(), TextField.TYPE_STORED));
                doc.add(new Field("key", publication.getKey(), TextField.TYPE_STORED));
                doc.add(new Field("title", publication.getTitle(), TextField.TYPE_STORED));
                if (publication.getAuthor() != null) {
                    doc.add(new Field("author", publication.getAuthor(), TextField.TYPE_STORED));
                }
                if (publication.getPublisher() != null) {
                    doc.add(new Field("publisher", publication.getPublisher(), TextField.TYPE_STORED));
                }
                if (publication.getIsbn() != null) {
                    doc.add(new Field("isbn", publication.getIsbn(), TextField.TYPE_STORED));
                }
                doc.add(new Field("keywords", publication.getKeywordsAsStringWithoutCommas(), TextField.TYPE_STORED));
                indexWriter.addDocument(doc);
            }

            indexWriter.close();

        } catch (IOException e) {
            System.out.println("Index rebuild failed.");
            e.printStackTrace();
        }
    }
}
