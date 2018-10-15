/**
 * Author: Felix Welter
 */
package de.nordakademie.iaa.library.dao;

import de.nordakademie.iaa.library.model.Keyword;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Named
public class KeywordDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Keyword> listKeywords() {
        return entityManager.createQuery("from Keyword", Keyword.class).getResultList();
    }

    public Keyword loadKeyword(Long keywordId) {
        Keyword keyword = (Keyword) entityManager.find(Keyword.class, keywordId);
        if (keyword == null) {
            throw new KeywordNotFoundException();
        }
        return keyword;
    }

    public Keyword findKeywordByWord(String word) {
        TypedQuery<Keyword> query = entityManager.createQuery("from Keyword l where l.word = :word", Keyword.class);
        query.setParameter("word", word);
        List<Keyword> keywords = query.getResultList();
        if (keywords.isEmpty()) {
            return null;
        }
        return keywords.get(0);
    }

    public void deleteKeyword(Long keywordId) {
        entityManager.remove(loadKeyword(keywordId));
    }

    public Keyword saveKeyword(Keyword keyword) {
        if (findKeywordByWord(keyword.getWord()) != null){
            throw new KeywordAlreadyExistsException();
        }
        entityManager.persist(keyword);
        return keyword;
    }
}
