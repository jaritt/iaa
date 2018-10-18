package de.nordakademie.iaa.library.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import de.nordakademie.iaa.library.dao.PublicationAlreadyExistsException;
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
        System.out.println("Action constructor");
    }

    private PublicationService publicationService;
    private KeywordService keywordService;
    private PublicationTypeService publicationTypeService;

    private Publication publication;
    private Long id;

    private List<PublicationType> publicationTypeList;
    private List<Keyword> keywordList;

    /**
     * @return List of PublicationTypes
     */
    public List<PublicationType> getPublicationTypeList() {
        return publicationTypeService.listPublicationTypes();
    }

    /**
     * @param publicationTypeList
     */
    public void setPublicationTypeList(List<PublicationType> publicationTypeList) {
        this.publicationTypeList = publicationTypeList;
    }

    public List<Keyword> getKeywordList() {
        return keywordService.listKeywords();
    }

    public void setKeywordList(List<Keyword> keywordList) {
        this.keywordList = keywordList;
    }

    /**
     * @return
     * @throws EntityNotFoundException
     */
    public String load() throws EntityNotFoundException {
        publication = publicationService.loadPublication(id);
        return SUCCESS;
    }

    /**
     * @return
     * @throws PublicationAlreadyExistsException
     */
    public String save() throws PublicationAlreadyExistsException {
        System.out.println("action.save");
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
            return SUCCESS;
        } else if (publication.getId() == null){
            publicationService.createPublication(publication);
            return SUCCESS;
        }
        return ERROR;
    }

    /**
     * @return
     * @throws EntityNotFoundException
     */
    public String delete() throws EntityNotFoundException {
        publicationService.deletePublication(id);
        return SUCCESS;
    }

    /*
    public void validationSave() {
        if (publication.getType() == null) {

        }
    }
    */

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

}