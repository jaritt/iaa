package de.nordakademie.iaa.library.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

public class HelloWorldAction extends ActionSupport{

    private List<String> searchEngine;

    private String yourSearchEngine;
    private String yourMonth;

    public String getYourMonth() {
        return yourMonth;
    }

    public void setYourMonth(String yourMonth) {
        this.yourMonth = yourMonth;
    }

    public List<String> getSearchEngine() {
        System.out.println("action.getSearchEngine");
        return searchEngine;
    }

    public void setSearchEngine(List<String> searchEngine) {
        System.out.println("action.setSearchEngine");
        this.searchEngine = searchEngine;
    }

    public String getYourSearchEngine() {
        System.out.println("action.getYourSearchEngine");
        return yourSearchEngine;
    }

    public void setYourSearchEngine(String yourSearchEngine) {
        System.out.println("action.setYourSearchEngine");
        this.yourSearchEngine = yourSearchEngine;
    }

    public String getDefaultSearchEngine() {
        System.out.println("action.getDefaultSearchEngine");
        return "yahoo.com";
    }

    public HelloWorldAction(){
        searchEngine = new ArrayList<String>();
        searchEngine.add("google.com");
        searchEngine.add("bing.com");
        searchEngine.add("yahoo.com");
        searchEngine.add("baidu.com");
        System.out.println("Action constructor");
    }

    public String execute() {
        System.out.println("action.execute");
        return SUCCESS;
    }

    public String display() {
        System.out.println("Action display");
        return NONE;
    }

}
