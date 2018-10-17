package de.nordakademie.iaa.library.action;

import com.opensymphony.xwork2.ActionSupport;
import de.nordakademie.iaa.library.dao.KeywordAlreadyExistsException;
import de.nordakademie.iaa.library.model.Keyword;
import de.nordakademie.iaa.library.service.api.KeywordService;

import javax.persistence.EntityNotFoundException;

public class KeywordAction extends ActionSupport {

    public KeywordAction(KeywordService keywordService) {
        this.keywordService = keywordService;
    }

    private KeywordService keywordService;

    private Long id;
    private Keyword keyword;

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

    /**
     * To implement validation for specific methods, use "validate" + "MethodeName" as method description.
     * Link below for further instruction.
     *
     * https://stackoverflow.com/questions/24968820/how-to-use-different-validate-methods-in-one-action-class-in-struts-2
     *
     */

    public void validateDelete() {
        if (id == null && keyword == null) {
            addActionError(getText("error.selectKeyword"));
        }
    }

    /**
     * waiting for Felix to implement method findKeywordByWord in KeywordServiceImpl
     */

    /*
    public void validateSave() {
        if (keyword. != null) {
            addActionError(getText("error."));
        }
    }
    */

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
