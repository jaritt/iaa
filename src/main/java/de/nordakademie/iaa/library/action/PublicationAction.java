package de.nordakademie.iaa.library.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import de.nordakademie.iaa.library.dao.publication.PublicationAlreadyExistsException;
import de.nordakademie.iaa.library.model.Keyword;
import de.nordakademie.iaa.library.model.Publication;
import de.nordakademie.iaa.library.model.PublicationType;
import de.nordakademie.iaa.library.service.api.KeywordService;
import de.nordakademie.iaa.library.service.api.PublicationService;
import de.nordakademie.iaa.library.service.api.PublicationTypeService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public class PublicationAction extends ActionSupport implements Action {

    public PublicationAction(
            PublicationService publicationService,
            KeywordService keywordService,
            PublicationTypeService publicationTypeService) {
        this.publicationService = publicationService;
        this.keywordService = keywordService;
        this.publicationTypeService = publicationTypeService;

        publicationTypeList = this.publicationTypeService.listPublicationTypes();
        keywordList = this.keywordService.listKeywords();
        System.out.println("Action constructor");
    }

    private PublicationService publicationService;
    private KeywordService keywordService;
    private PublicationTypeService publicationTypeService;

    private Publication publication;
    private Long id;

    private List<PublicationType> publicationTypeList;
    private PublicationType selectedPublicationType;

    private List<Keyword> keywordList;
    private List<Keyword> selectedKeywords;
    private Keyword selectedKeyword;

    public String getSelectedKeyword() {
        return selectedKeyword.getWord();
    }

    public void setSelectedKeyword(Keyword selectedKeyword) {
        this.selectedKeyword = selectedKeyword;
    }

    public List<Keyword> getSelectedKeywords() {
        return selectedKeywords;
    }

    public void setSelectedKeywords(List<Keyword> selectedKeywords) {
        this.selectedKeywords = selectedKeywords;
    }

    public List<PublicationType> getPublicationTypeList() {
        System.out.println("action.getPublicationTypesList");
        System.out.println("class: " + publicationTypeService.listPublicationTypes().getClass());
        return publicationTypeService.listPublicationTypes();
    }

    public void setPublicationTypeList(List<PublicationType> publicationTypeList) {
        System.out.println("action.setPublicationTypesList");
        this.publicationTypeList = publicationTypeList;
    }

    public List<Keyword> getKeywordList() {
        System.out.println("action.getKeywordList");
        System.out.println("class: " + keywordService.listKeywords().getClass());
        return keywordService.listKeywords();
    }

    public void setKeywordList(List<Keyword> keywordList) {
        System.out.println("action.setKeywordList");
        this.keywordList = keywordList;
    }

    public PublicationType getSelectedPublication() {
        System.out.println("action.getSelectedPublication");
        return selectedPublicationType;
    }

    public void setSelectedPublicationType(PublicationType selectedPublicationType) {
        System.out.println("action.setSelectedPublicationType");
        this.selectedPublicationType = selectedPublicationType;
    }

    public PublicationType defaultSelectedPublicationType(){
        System.out.println("action.defaultSelectedPublicationType");
        return publicationTypeService.listPublicationTypes().get(0);
    }

    public String execute() {
        System.out.println("action.execute");
        return SUCCESS;
    }

    public String display() {
        System.out.println("action.display");
        return NONE;
    }

    public String load() throws EntityNotFoundException {
        publication = publicationService.loadPublication(id);
        return SUCCESS;
    }

    public String save() throws PublicationAlreadyExistsException {
        System.out.println("action.save");

        publication.setType(selectedPublicationType);
        publication.setKeywords(selectedKeywords);

        if (publication.getId() != null) {
            System.out.println("Action.updatePublication");
            publicationService.updatePublication(
                    publication.getId(),
                    publication.getTitle(),
                    publication.getAuthor(),
                    publication.getReleaseDate(),
                    publication.getPublisher(),
                    publication.getType(),
                    publication.getIsbn(),
                    publication.getKeywords(),
                    publication.getCopies());
        } else {
            System.out.println("action.createPublication");
            publicationService.createPublication(publication);
        }
        return SUCCESS;
    }

    public String delete() throws EntityNotFoundException {
        publicationService.deletePublication(id);
        return SUCCESS;
    }

    public void validateSave() {
        if (!publication.getTitle().getClass().equals(String.class)){
            System.out.println("validate.Titel");
            System.out.println(publication.getTitle().getClass() + " == " + String.class);
            addActionError(getText("error.publicationTitle"));
        }
    }

    public void validateLoad() {
        if (id == null && publication == null) {
            addActionError(getText("error.selectPublication"));
        }
    }

    public void validateDelete() {
        if (id == null && publication == null) {
            addActionError(getText("error.selectPublication"));
        }
    }

    public Long getPublicationId() {
        return id;
    }

    public void setPublicationId(Long id) {
        this.id = id;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        System.out.println("action.setPublication");
        this.publication = publication;
    }

}