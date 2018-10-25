package de.nordakademie.iaa.library.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import de.nordakademie.iaa.library.dao.publication.PublicationAlreadyExistsException;
import de.nordakademie.iaa.library.model.PublicationType;
import de.nordakademie.iaa.library.service.api.PublicationTypeService;

import javax.persistence.EntityNotFoundException;

public class PublicationTypeAction extends ActionSupport implements Action {
    public PublicationTypeAction(PublicationTypeService publicationTypeService) {
        this.publicationTypeService = publicationTypeService;
    }

    private PublicationTypeService publicationTypeService;

    private Long publicationTypeId;
    private PublicationType publicationType;

    public String load() throws EntityNotFoundException {
        publicationType = publicationTypeService.loadPublicationType(publicationTypeId);
        return SUCCESS;
    }

    public String save() throws PublicationAlreadyExistsException {
        if (publicationType.getId() != null){
            publicationTypeService.updatePublicationType(publicationType.getId(), publicationType.getTitle());
        }
        else {
            publicationTypeService.createPublicationType(publicationType);
        }
        return SUCCESS;
    }

    public String delete() throws EntityNotFoundException {
        publicationTypeService.deletePublicationType(publicationTypeId);
        return SUCCESS;
    }

    public void validateSave() {
        if (publicationTypeService.findPublicationTypeByTitle(publicationType.getTitle()) != null) {
            addActionError(getText("error.publicationTypeAlreadyExists"));
        }
    }

    public void validateLoad() {
        if (publicationTypeId == null && publicationType == null) {
            addActionError(getText("error.selectPublicationType"));
        }
    }

    public void validateDelete() {
        if (publicationTypeId == null && publicationType == null) {
            addActionError(getText("error.selectPublicationType"));
        }
    }

    public Long getPublicationTypeId() {
        return publicationTypeId;
    }

    public void setPublicationTypeId(Long publicationTypeId) {
        this.publicationTypeId = publicationTypeId;
    }

    public PublicationType getPublicationType() {
        return publicationType;
    }

    public void setPublicationType(PublicationType publicationType) {
        this.publicationType = publicationType;
    }
}

