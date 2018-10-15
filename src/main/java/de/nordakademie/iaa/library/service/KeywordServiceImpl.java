package de.nordakademie.iaa.library.service;

import de.nordakademie.iaa.library.dao.KeywordDAO;
import de.nordakademie.iaa.library.model.Keyword;
import de.nordakademie.iaa.library.service.api.KeywordService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public class KeywordServiceImpl implements KeywordService {

    private KeywordDAO dao;

    public KeywordServiceImpl(KeywordDAO dao) {
        this.dao = dao;
    }

    @Override
    public void createKeyword(Keyword keyword) {
        dao.saveKeyword(keyword);
    }

    @Override
    public List<Keyword> listKeywords() {
        return dao.listKeywords();
    }

    @Override
    public void deleteKeyword(Long id) throws EntityNotFoundException {
        Keyword keyword = loadKeyword(id);
        if (keyword == null) {
            throw new EntityNotFoundException();
        }
        dao.deleteKeyword(keyword.getId());
    }

    @Override
    public Keyword loadKeyword(Long id) {
        return dao.loadKeyword(id);
    }

    @Override
    public void updateKeyword(Long id, String word) throws EntityNotFoundException {
        Keyword keyword = loadKeyword(id);
        if (keyword == null) {
            throw new EntityNotFoundException();
        }
        keyword.setWord(word);
    }
}
