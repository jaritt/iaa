package de.nordakademie.iaa.library.service;

import de.nordakademie.iaa.library.dao.KeywordDAO;
import de.nordakademie.iaa.library.model.Keyword;
import de.nordakademie.iaa.library.service.api.KeywordService;

import javax.inject.Named;
import javax.persistence.EntityNotFoundException;
import java.util.List;

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
}
