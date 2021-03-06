package de.nordakademie.iaa.library.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import de.nordakademie.iaa.library.dao.keyword.KeywordAlreadyExistsException;
import de.nordakademie.iaa.library.model.Keyword;
import de.nordakademie.iaa.library.service.api.KeywordService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * @author Jannis Bär & Vikash Sharma
 */

public class KeywordAction extends ActionSupport implements Action {

    public KeywordAction(KeywordService keywordService) {
        this.keywordService = keywordService;
    }

    private KeywordService keywordService;

    private Long id;
    private Keyword keyword;
    private List<Keyword> keywordList;

    public String load() throws EntityNotFoundException {
        keyword = keywordService.loadKeyword(id);
        return SUCCESS;
    }

    public String save() throws KeywordAlreadyExistsException {
        if (keyword.getId() != null) {
            keywordService.updateKeyword(keyword.getId(), keyword.getWord());
        } else {
            keywordService.createKeyword(keyword);
        }
        return SUCCESS;
    }

    public String delete() throws EntityNotFoundException {
        keywordService.deleteKeyword(id);
        return SUCCESS;
    }

    public String getKeywords() throws Exception {
        keywordService.listKeywords();
        return SUCCESS;
    }

    /**
     * To implement validation for specific methods, use "validate" + "MethodeName" as method description.
     * Link below for further instruction.
     *
     * https://stackoverflow.com/questions/24968820/how-to-use-different-validate-methods-in-one-action-class-in-struts-2
     *
     */

    public void validateSave() {
        if (keywordService.findKeywordByWord(keyword.getWord()) != null) {
            addActionError(getText("error.keywordAlreadyExists"));
        }
    }

    public void validateLoad() {
        if (id == null && keyword == null) {
            addActionError(getText("error.selectKeyword"));
        }
    }

    public void validateDelete() {
        if (id == null && keyword == null) {
            addActionError(getText("error.selectKeyword"));
        }
    }


    public Long getKeywordId() {
        return id;
    }

    public void setKeywordId(Long id) {
        this.id = id;
    }

    public Keyword getKeyword() {
        return keyword;
    }

    public void setKeyword(Keyword keyword) {
        this.keyword = keyword;
    }
}
