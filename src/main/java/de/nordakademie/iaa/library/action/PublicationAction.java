package de.nordakademie.iaa.library.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import de.nordakademie.iaa.library.dao.publication.PublicationAlreadyExistsException;
import de.nordakademie.iaa.library.model.Keyword;
import de.nordakademie.iaa.library.model.Lending;
import de.nordakademie.iaa.library.model.Publication;
import de.nordakademie.iaa.library.model.PublicationType;
import de.nordakademie.iaa.library.service.api.KeywordService;
import de.nordakademie.iaa.library.service.api.LendingService;
import de.nordakademie.iaa.library.service.api.PublicationService;
import de.nordakademie.iaa.library.service.api.PublicationTypeService;

import javax.persistence.EntityNotFoundException;
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
        System.out.println("PublicationAction constructor");
    }

    private PublicationService publicationService;
    private KeywordService keywordService;
    private PublicationTypeService publicationTypeService;
    private LendingService lendingService;

    private Publication publication;
    private Long publicationId;

    private List<PublicationType> publicationTypeList;
    private Long selectedTypeId;

    private List<Long> keywordIds;
    private List<Keyword> keywords;

    private List<Keyword> keywordList;
    private List<Lending> lendingList;

    private List<Lending> openLendings;

    public String load() throws EntityNotFoundException {
        lendingList = lendingService.listLendings();
        publication = publicationService.loadPublication(publicationId);
        publicationTypeList = publicationTypeService.listPublicationTypes();
        keywordList = keywordService.listKeywords();
        openLendings = publication.getLendings().stream().filter(lending -> !lending.isCompleted()).collect(Collectors.toList());
        return SUCCESS;
    }

    public String save() throws PublicationAlreadyExistsException {
        publication.setType(publicationTypeService.loadPublicationType(selectedTypeId));
        publication.setKeywords(this.keywords);

        if (publication.getId() != null) {
            publicationService.updatePublication(
                    publication.getId(),
                    publication.getTitle(),
                    publication.getAuthor(),
                    publication.getReleaseDay(),
                    publication.getReleaseMonth(),
                    publication.getReleaseYear(),
                    publication.getPublisher(),
                    publication.getType(),
                    publication.getIsbn(),
                    publication.getKeywords(),
                    publication.getCopies());
        } else {
            publicationService.createPublication(publication);
            }
        return SUCCESS;
    }

    public String delete() throws EntityNotFoundException {
        publicationService.deletePublication(publicationId);
        return SUCCESS;
    }

    public void validateSave() {
        if (selectedTypeId == -1){
            addActionError(getText("error.selectPublicationType"));
        }

        if (!(publication.getIsbn().equals(""))) {
            if (publicationService.findPublicationByISBN(publication.getIsbn()) != null) {
                addActionError(getText("error.publicationAlreadyExists"));
            }
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

    public List<PublicationType> getPublicationTypeList() {
        return publicationTypeService.listPublicationTypes();
    }

    public void setPublicationTypeList(List<PublicationType> publicationTypeList) {
        this.publicationTypeList = publicationTypeList;
    }

    public List<Keyword> getKeywordList() {
        return keywordService.listKeywords();
    }

    public void setKeywordList(List<Keyword> keywordList) {
        this.keywordList = keywordList;
    }

    public List<Lending> getLendingList() {
        return lendingService.listLendings();
    }

    public void setLendingList(List<Lending> lendingList) {
        this.lendingList = lendingList;
    }

    public Long getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(Long publicationId) {
        this.publicationId = publicationId;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public Long getSelectedTypeId() {
        return selectedTypeId;
    }

    public void setSelectedTypeId(Long selectedTypeId) {
        this.selectedTypeId = selectedTypeId;
        publication.setType(publicationTypeService.loadPublicationType(selectedTypeId));
    }

    public List<Long> getKeywordIds() {
        return this.publication.getKeywords().stream().map(Keyword::getId).collect(Collectors.toList());
    }

    /*
    public List<Long> getLendingIds)() {
        return this.publication.getLendings().stream().map(Lending::getId).collect(Collectors.toList());
    }
    */

    public void setKeywordIds(List<Long> keywordIds) {
        this.keywordIds = keywordIds;
        this.keywords = keywordService.listKeywords(keywordIds);
    }

    public List<Lending> getOpenLendings() {
        return openLendings;
    }

    public void setOpenLendings(List<Lending> openLendings) {
        this.openLendings = openLendings;
    }
}