package de.nordakademie.iaa.library.action;

import com.opensymphony.xwork2.ActionSupport;
import de.nordakademie.iaa.library.model.Keyword;
import de.nordakademie.iaa.library.service.api.KeywordService;

public class KeywordAction extends ActionSupport {

    public KeywordAction(KeywordService keywordService) {
        this.keywordService = keywordService;
    }

    private KeywordService keywordService;

    private Long id;
    private String word;
    private Keyword keyword;

    public String load() throws Exception{
        keyword = keywordService.loadKeyword(id);
        return SUCCESS;
    }

    public String save() throws Exception{
        if (keyword.getId() != null){
            keywordService.updateKeyword(keyword.getId(), keyword.getWord());
        }
        else{
            keywordService.createKeyword(keyword);
        }
        return SUCCESS;
    }

    public String delete() throws Exception{
        keywordService.deleteKeyword(id);
        return SUCCESS;
    }

    public Long getKeywordId() {
        return id;
    }

    public void setKeywordId(Long id) {
        this.id = id;
    }

    public String getWord() { return word; }

    public void setWord(String word) { this.word = word; }

    public Keyword getKeyword(){ return keyword; }

    public void setKeyword(Keyword keyword) { this.keyword = keyword; }
}
