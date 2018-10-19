package de.nordakademie.iaa.library.dao.keyword;

import de.nordakademie.iaa.library.model.Keyword;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * This class gives access to keywords.
 *
 * @author Felix Welter
 * @see Keyword
 */
@Named
public class KeywordDAO {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * List all keywords
     *
     * @return list of keywords
     */
    public List<Keyword> listKeywords() {
        return entityManager.createQuery("from Keyword", Keyword.class).getResultList();
    }

    /**
     * Load a specific keyword identified by its id
     *
     * @param keywordId keyword identifier
     * @return the requested keyword
     */
    public Keyword loadKeyword(Long keywordId) {
        Keyword keyword = entityManager.find(Keyword.class, keywordId);
        if (keyword == null) {
            throw new KeywordNotFoundException();
        }
        return keyword;
    }

    /**
     * Find the keyword specific by the word itself
     *
     * @param word the word to search for
     * @return the requested keyword
     */
    public Keyword findKeywordByWord(String word) {
        TypedQuery<Keyword> query = entityManager.createQuery("from Keyword l where l.word = :word", Keyword.class);
        query.setParameter("word", word);
        List<Keyword> keywords = query.getResultList();
        if (keywords.isEmpty()) {
            return null;
        }
        return keywords.get(0);
    }

    /**
     * Delete a specific keyword
     *
     * @param keywordId keyword identifier
     */
    public void deleteKeyword(Long keywordId) {
        entityManager.remove(loadKeyword(keywordId));
    }

    /**
     * Save a new keyword to the database
     *
     * @param keyword a new keyword
     * @return the persisted keyword
     */
    public Keyword saveKeyword(Keyword keyword) {
        if (findKeywordByWord(keyword.getWord()) != null) {
            throw new KeywordAlreadyExistsException();
        }
        entityManager.persist(keyword);
        return keyword;
    }
}
