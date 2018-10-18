package de.nordakademie.iaa.library.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import de.nordakademie.iaa.library.dao.PublicationAlreadyExistsException;
import de.nordakademie.iaa.library.model.Keyword;
import de.nordakademie.iaa.library.model.Publication;
import de.nordakademie.iaa.library.model.PublicationType;
import de.nordakademie.iaa.library.service.api.KeywordService;
import de.nordakademie.iaa.library.service.api.PublicationService;
import de.nordakademie.iaa.library.service.api.PublicationTypeService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public class PublicationAction extends ActionSupport implements Action, Preparable {

    public PublicationAction(
            PublicationService publicationService,
            KeywordService keywordService,
            PublicationTypeService publicationTypeService) {
        this.publicationService = publicationService;
        this.keywordService = keywordService;
        this.publicationTypeService = publicationTypeService;
    }

    private PublicationService publicationService;
    private KeywordService keywordService;
    private PublicationTypeService publicationTypeService;

    private Publication publication;
    private Long id;

    private List<PublicationType> publicationTypeList;
    private List<Keyword> keywordList;

    public String load() throws EntityNotFoundException {
        publication = publicationService.loadPublication(id);
        this.keywordList = keywordService.listKeywords();
        return SUCCESS;
    }

    public String save() throws PublicationAlreadyExistsException {
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
        publicationService.deletePublication(id);
        return SUCCESS;
    }

    public String publicationTypeList() throws Exception{
        this.publicationTypeList = publicationTypeService.listPublicationTypes();
        return SUCCESS;
    }

    /**
     * validation
     */

    //validationSave()

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
        this.publication = publication;
    }

    public List<PublicationType> getPublicationTypeList() {
        return publicationTypeList;
    }

    public void setPublicationTypeList(List<PublicationType> publicationTypeList) {
        this.publicationTypeList = publicationTypeList;
    }

    public List<Keyword> getKeywordList() {
        return keywordList;
    }

    public void setKeywordList(List<Keyword> keywordList) {
        this.keywordList = keywordList;
    }

    @Override
    public void prepare() throws Exception {

    }
}