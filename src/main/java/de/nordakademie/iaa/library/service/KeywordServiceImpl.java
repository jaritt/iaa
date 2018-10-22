package de.nordakademie.iaa.library.service;

import de.nordakademie.iaa.library.dao.keyword.KeywordDAO;
import de.nordakademie.iaa.library.model.Keyword;
import de.nordakademie.iaa.library.service.api.KeywordService;

import javax.inject.Named;
import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Implements functions defined by KeywordService
 *
 * @author Felix Welter
 * @see KeywordService
 */
@Named
public class KeywordServiceImpl implements KeywordService {

    private KeywordDAO dao;

    public KeywordServiceImpl(KeywordDAO dao) {
        this.dao = dao;
    }

    /**
     * @param keyword The keyword to be persisted.
     */
    @Override
    public void createKeyword(Keyword keyword) {
        dao.saveKeyword(keyword);
    }

    /**
     * Returns available keywords
     *
     * @return List of keywords
     */
    @Override
    public List<Keyword> listKeywords() {
        return dao.listKeywords();
    }

    /**
     * Returns several keywords identified by a list of ids
     *
     * @param idList The identifiers.
     * @return The requested keyword
     */
    @Override
    public List<Keyword> listKeywords(List<Long> idList) {
        return dao.listKeywords(idList);
    }

    /**
     * Deletes a keyword
     *
     * @param id The identifier.
     * @throws EntityNotFoundException
     */
    @Override
    public void deleteKeyword(Long id) throws EntityNotFoundException {
        Keyword keyword = loadKeyword(id);
        if (keyword == null) {
            throw new EntityNotFoundException();
        }
        dao.deleteKeyword(keyword.getId());
    }

    /**
     * Load a keyword
     *
     * @param id The identifier.
     * @return The requested keyword
     */
    @Override
    public Keyword loadKeyword(Long id) {
        return dao.loadKeyword(id);
    }


    /**
     * Update a keyword
     *
     * @param id
     * @param word
     * @throws EntityNotFoundException
     */
    @Override
    public void updateKeyword(Long id, String word) throws EntityNotFoundException {
        Keyword keyword = loadKeyword(id);
        if (keyword == null) {
            throw new EntityNotFoundException();
        }
        keyword.setWord(word);
    }


    /**
     * Finds a keyword identified by the word itself
     *
     * @param word The word to search for
     * @return The keyword that is searched for
     */
    @Override
    public Keyword findKeywordByWord(String word) {
        return dao.findKeywordByWord(word);
    }
}
