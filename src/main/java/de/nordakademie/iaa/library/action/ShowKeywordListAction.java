package de.nordakademie.iaa.library.action;

import com.opensymphony.xwork2.Action;
import de.nordakademie.iaa.library.model.Keyword;
import de.nordakademie.iaa.library.service.api.KeywordService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * @author Jannis Bär & Vikash Sharma
 */

public class ShowKeywordListAction implements Action{

    public ShowKeywordListAction(KeywordService keywordService) {

        this.keywordService = keywordService;
    }

    private KeywordService keywordService;

    private List<Keyword> keywords;

    @Override
    public String execute() throws EntityNotFoundException {
        keywords = keywordService.listKeywords();
        return SUCCESS;
    }

    public List<Keyword> getKeywords() {return keywords;}
}
