package de.nordakademie.iaa.library.action;

import com.opensymphony.xwork2.Action;
import de.nordakademie.iaa.library.model.Keyword;
import de.nordakademie.iaa.library.model.Publication;
import de.nordakademie.iaa.library.model.PublicationType;
import de.nordakademie.iaa.library.service.api.KeywordService;
import de.nordakademie.iaa.library.service.api.PublicationService;
import de.nordakademie.iaa.library.service.api.PublicationTypeService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public class ShowPublicationListAction implements Action{

    public ShowPublicationListAction(PublicationService publicationService,
                                     PublicationTypeService publicationTypeService,
                                     KeywordService keywordService) {

        this.publicationService = publicationService;
        this.publicationTypeService = publicationTypeService;
        this.keywordService = keywordService;
    }

    private PublicationService publicationService;
    private PublicationTypeService publicationTypeService;
    private KeywordService keywordService;

    private List<Publication> publications;

    private List<PublicationType> publicationTypes;
    private List<Keyword> keywords;

    private String searchTerm;

    @Override
    public String execute() throws EntityNotFoundException {
        if (searchTerm == null || searchTerm == "") {
            publications = publicationService.listPublications();
        } else {
            publications = publicationService.search(searchTerm);
        }
        publicationTypes = publicationTypeService.listPublicationTypes();
        keywords = keywordService.listKeywords();
        return SUCCESS;
    }

    public List<Publication> getPublications() {return publications;}

    public List<PublicationType> getPublicationTypes() { return publicationTypes; }

    public List<Keyword> getKeywords() { return keywords; }

    public void setPublications(List<Publication> publications) { this.publications = publications; }

    public void setPublicationTypes(List<PublicationType> publicationTypes) { this.publicationTypes = publicationTypes; }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }

    public PublicationService getPublicationService() {
        return publicationService;
    }

    public void setPublicationService(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    public PublicationTypeService getPublicationTypeService() {
        return publicationTypeService;
    }

    public void setPublicationTypeService(PublicationTypeService publicationTypeService) {
        this.publicationTypeService = publicationTypeService;
    }

    public KeywordService getKeywordService() {
        return keywordService;
    }

    public void setKeywordService(KeywordService keywordService) {
        this.keywordService = keywordService;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }
}
