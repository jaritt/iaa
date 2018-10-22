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
import net.bytebuddy.asm.Advice;

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
        System.out.println("PublicationAction constructor");
    }

    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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

    private List<Lending> lendingList;

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

    public String load() throws EntityNotFoundException {
        lendingList = lendingService.listLendings();
        publication = publicationService.loadPublication(publicationId);
        publicationTypeList = publicationTypeService.listPublicationTypes();
        keywordList = keywordService.listKeywords();
        return SUCCESS;
    }

    public String save() throws PublicationAlreadyExistsException {
        publication.setType(publicationTypeService.loadPublicationType(selectedTypeId));
        publication.setKeywords(this.keywords);
        publication.setReleaseDate(this.releaseDate = convertStringToDate(publicationDate));

        if (publication.getId() != null) {
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
            publicationService.createPublication(publication);
        }
        return SUCCESS;
    }

    public String delete() throws EntityNotFoundException {
        publicationService.deletePublication(publicationId);
        return SUCCESS;
    }

    public void validateSave() {
        if (selectedTypeId == 0){
            addActionError(getText("error.selectPublicationType"));
        }

        /**
         *
         * Warten bis Felix die Methode im Service implementiert hat.
         *
         */

        /*
        if (findPublicationByIsbn) {

        }
        */

        /**
         *
         * Es ist noch zu klären, ob das Datum nicht einfach ein String sein kann.
         * Validierung bis auf weiteres auskommentiert.
         *
         */

        /*
        if (!publicationDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
            addActionError(getText("error.publicationReleaseDate"));
            addActionMessage(getText("error.publicationReleaseDate"));
            addFieldError(publicationDate, "Fail");
        }
        */
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

    public void setKeywordIds(List<Long> keywordIds) {
        this.keywordIds = keywordIds;
        this.keywords = keywordService.listKeywords(keywordIds);
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        publication.setReleaseDate(convertStringToDate(publicationDate));
    }

    public String getPublicationDate() {
        return publicationDate = publication.getReleaseDate().toString();
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public List<Lending> getLendingList() {
        return lendingService.listLendings();
    }

    public void setLendingList(List<Lending> lendingList) {
        this.lendingList = lendingList;
    }

    public LocalDate convertStringToDate(String publicationDate) {
        return releaseDate = LocalDate.parse(publicationDate, formatter);
    }

    public String convertDateToString (LocalDate releaseDate) {
        return publicationDate = releaseDate.toString();
    }

}