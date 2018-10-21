package de.nordakademie.iaa.library.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import de.nordakademie.iaa.library.dao.publication.PublicationAlreadyExistsException;
import de.nordakademie.iaa.library.model.Keyword;
import de.nordakademie.iaa.library.model.Publication;
import de.nordakademie.iaa.library.model.PublicationType;
import de.nordakademie.iaa.library.service.api.KeywordService;
import de.nordakademie.iaa.library.service.api.LendingService;
import de.nordakademie.iaa.library.service.api.PublicationService;
import de.nordakademie.iaa.library.service.api.PublicationTypeService;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PublicationAction extends ActionSupport implements Action {

    public PublicationAction(
            PublicationService publicationService,
            KeywordService keywordService,
            PublicationTypeService publicationTypeService,
            LendingService lendingService) {
        this.publicationService = publicationService;
        this.keywordService = keywordService;
        this.publicationTypeService = publicationTypeService;
        this.lendingService = lendingService;

        publicationTypeList = this.publicationTypeService.listPublicationTypes();
        keywordList = this.keywordService.listKeywords();
        System.out.println("Action constructor");
    }

    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");

    private PublicationService publicationService;
    private KeywordService keywordService;
    private PublicationTypeService publicationTypeService;
    private LendingService lendingService;

    private Publication publication;
    private Long publicationId;
    private Long selectedTypeId;

    //private List<Long> selectedKeywordIds;

    private Long selectedKeywordId;

    private String publicationDate;
    private LocalDate releaseDate;

    private List<PublicationType> publicationTypeList;
    private List<Keyword> keywordList;

    public List<PublicationType> getPublicationTypeList() {
        System.out.println("action.getPublicationTypesList " + publicationTypeService.listPublicationTypes().getClass());
        return publicationTypeService.listPublicationTypes();
    }

    public void setPublicationTypeList(List<PublicationType> publicationTypeList) {
        System.out.println("action.setPublicationTypesList");
        this.publicationTypeList = publicationTypeList;
    }

    public List<Keyword> getKeywordList() {
        System.out.println("action.getKeywordList " + keywordService.listKeywords().getClass().toString());
        System.out.println();
        return keywordService.listKeywords();
    }

    public void setKeywordList(List<Keyword> keywordList) {
        System.out.println("action.setKeywordList");
        this.keywordList = keywordList;
    }

    public String load() throws EntityNotFoundException {
        publication = publicationService.loadPublication(publicationId);
        publicationTypeList = publicationTypeService.listPublicationTypes();
        keywordList = keywordService.listKeywords();
        return SUCCESS;
    }

    public String save() throws PublicationAlreadyExistsException {
        System.out.println("action.save");

        publication.setType(publicationTypeService.loadPublicationType(selectedTypeId));
        System.out.println("action.save SelectedTypeId: " + selectedTypeId);
        System.out.println("action.save SelectedTypeObject: " + publicationTypeService.loadPublicationType(selectedTypeId));
        System.out.println("action.save SelectedTypeTitle: " + publicationTypeService.loadPublicationType(selectedTypeId).getTitle());
        System.out.println("action.save getPublicationype: " + publication.getType());

        if (publication.getId() != null) {
            System.out.println("action.updatePublication");
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
        publicationService.deletePublication(publicationId);
        return SUCCESS;
    }

    public String lend() throws EntityNotFoundException {
        publication = publicationService.loadPublication(publicationId);
        publicationTypeList = publicationTypeService.listPublicationTypes();
        keywordList = keywordService.listKeywords();
        return SUCCESS;
    }

    public void validateSave() {
        if (!publication.getTitle().getClass().equals(String.class)){
            System.out.println("validate.publicationTitel");
            System.out.println(publication.getTitle().getClass() + " == " + String.class);
            addActionError(getText("error.publicationTitle"));
        }
    }

    public void validateLoad() {
        if (publicationId == null && publication == null) {
            addActionError(getText("error.selectPublication"));
        }
    }

    public void validateDelete() {
        if (publicationId == null && publication == null) {
            addActionError(getText("error.selectPublication"));
        }
    }

    public void validateLend() {
        if (publicationId == null && publication == null) {
            addActionError(getText("error.selectPublication"));
        }
    }

    public Long getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(Long id) {
        this.publicationId = id;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        System.out.println("action.setPublication");
        this.publication = publication;
    }

    public Long getSelectedTypeId() {
        System.out.println("action.getSelectedTypeId");
        return selectedTypeId;
    }

    public void setSelectedTypeId(Long selectedTypeId) {
        System.out.println("action.setSelectedTypeId");
        this.selectedTypeId = selectedTypeId;
        publication.setType(publicationTypeService.loadPublicationType(selectedTypeId));
    }

    /*
    public List<Long> getSelectedKeywordIds() {
        System.out.println("action.getSelectedKeywordIds");
        return selectedKeywordIds;
    }
    /*

    /**
     * @Todo
     * Der KeywordService sollte die Möglichkeit haben,
     * eine List mit den selektierten Keywords zu erhalten.
     *
     */

    /*
    public void setSelectedKeywordIds(List<Long> selectedKeywordIds) {
        System.out.println("action.setSelectedKeywordIds");
        System.out.println("--> SelectedKeywordIds: " + selectedKeywordIds);
        System.out.println("--> KeywordService.listKeywords(): " + keywordService.listKeywords());
        this.selectedKeywordIds = selectedKeywordIds;
        publication.setKeywords(keywordService.listKeywords());
    }
    */

    public String getPublicationDate() {
        System.out.println("action.getPublicationDate");
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        System.out.println("action.setPublicationDate");
        this.publicationDate = publicationDate;
        publication.setReleaseDate(convertStringToDate(publicationDate));
    }

    public LocalDate convertStringToDate(String publicationDate) {
        System.out.println("action.convertStringToDate");
        return releaseDate = LocalDate.parse(publicationDate, formatter);
    }
}