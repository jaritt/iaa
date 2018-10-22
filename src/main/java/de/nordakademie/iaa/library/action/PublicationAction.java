package de.nordakademie.iaa.library.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import de.nordakademie.iaa.library.model.Keyword;
import de.nordakademie.iaa.library.model.Publication;
import de.nordakademie.iaa.library.model.PublicationType;
import de.nordakademie.iaa.library.service.api.KeywordService;
import de.nordakademie.iaa.library.service.api.LendingService;
import de.nordakademie.iaa.library.service.api.PublicationService;
import de.nordakademie.iaa.library.service.api.PublicationTypeService;
import de.nordakademie.iaa.library.dao.publication.PublicationAlreadyExistsException;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

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
        System.out.println("---------------------------- ");
        System.out.println(" ");
        System.out.println("PublicationAction constructor");
    }

    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");

    private PublicationService publicationService;
    private KeywordService keywordService;
    private PublicationTypeService publicationTypeService;
    private LendingService lendingService;

    private Publication publication;
    private Long publicationId;
    private Long selectedTypeId;

    private List<Long> keywordIds;
    private List<Keyword> keywords;

    private String publicationDate;
    private LocalDate releaseDate;

    private List<PublicationType> publicationTypeList;
    private List<Keyword> keywordList;

    public List<PublicationType> getPublicationTypeList() {
        System.out.println("PublicationAction --> getPublicationTypesList");
        return publicationTypeService.listPublicationTypes();
    }

    public void setPublicationTypeList(List<PublicationType> publicationTypeList) {
        System.out.println("PublicationAction --> setPublicationTypesList");
        this.publicationTypeList = publicationTypeList;
    }

    public List<Keyword> getKeywordList() {
        System.out.println("PublicationAction --> getKeywordList");
        System.out.println();
        return keywordService.listKeywords();
    }

    public void setKeywordList(List<Keyword> keywordList) {
        System.out.println("PublicationAction --> setKeywordList");
        this.keywordList = keywordList;
    }

    public String load() throws EntityNotFoundException {
        System.out.println("PublicationAction --> method: load()");
        System.out.println("PublicationAction --> method: load() --> PublicationId: " + publicationId);
        publication = publicationService.loadPublication(publicationId);
        publicationTypeList = publicationTypeService.listPublicationTypes();
        keywordList = keywordService.listKeywords();
        System.out.println("PublicationAction --> method: load() --> PublicationId: " + publication.getId());
        return SUCCESS;
    }

    public String save() throws PublicationAlreadyExistsException {
        System.out.println("PublicationAction --> method: save()");

        publication.setType(publicationTypeService.loadPublicationType(selectedTypeId));
        publication.setKeywords(this.keywords);

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
        if (!publication.getTitle().getClass().equals(String.class)) {
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

    public Publication getPublication() {
        System.out.println("PublicationAction --> getPublication: " + publication.getId() + " " + publication.getTitle());
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
        System.out.println("PublicationAction --> setPublication: " + publication.getId() + " " + publication.getTitle());
    }

    public Long getPublicationId() {
        System.out.println("PublicationAction --> getPublicationId: " + publicationId);
        return publicationId;
    }

    public void setPublicationId(Long id) {
        System.out.println("PublicationAction --> setPublicationId: " + id);
        this.publicationId = id;
        System.out.println("PublicationAction --> setPublicationId: " + publicationId);
    }

    public Long getSelectedTypeId() {
        System.out.println("PublicationAction --> getSelectedTypeId: " + selectedTypeId);;
        return selectedTypeId;
    }

    public void setSelectedTypeId(Long selectedTypeId) {
        this.selectedTypeId = selectedTypeId;
        publication.setType(publicationTypeService.loadPublicationType(selectedTypeId));
        System.out.println("PublicationAction --> setSelectedTypeId: " + selectedTypeId);
    }

    public List<Long> getKeywordIds() {
        return this.publication.getKeywords().stream().map(Keyword::getId).collect(Collectors.toList());
    }

    public void setKeywordIds(List<Long> keywordIds) {
        this.keywordIds = keywordIds;
        this.keywords = keywordService.listKeywords(keywordIds);
    }

    public String getPublicationDate() {
        System.out.println("action.getPublicationDate");
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
        publication.setReleaseDate(convertStringToDate(publicationDate));
        System.out.println("PublicationAction --> setPublicationDate: " + publicationDate);
    }

    public LocalDate convertStringToDate(String publicationDate) {
        System.out.println("PublicationAction --> convertStringToDate:" + publicationDate + " " + LocalDate.parse(publicationDate, formatter));
        return releaseDate = LocalDate.parse(publicationDate, formatter);
    }
}