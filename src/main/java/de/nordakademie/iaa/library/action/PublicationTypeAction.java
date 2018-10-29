package de.nordakademie.iaa.library.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import de.nordakademie.iaa.library.dao.publication.PublicationAlreadyExistsException;
import de.nordakademie.iaa.library.model.Publication;
import de.nordakademie.iaa.library.model.PublicationType;
import de.nordakademie.iaa.library.service.api.PublicationService;
import de.nordakademie.iaa.library.service.api.PublicationTypeService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jannis Bär & Vikash Sharma
 */

public class PublicationTypeAction extends ActionSupport implements Action {

    public PublicationTypeAction(PublicationTypeService publicationTypeService, PublicationService publicationService) {
        this.publicationTypeService = publicationTypeService;
        this.publicationService = publicationService;
    }

    private PublicationTypeService publicationTypeService;
    private PublicationService publicationService;

    private Long publicationTypeId;
    private PublicationType publicationType;

    public String load() throws EntityNotFoundException {
        publicationType = publicationTypeService.loadPublicationType(publicationTypeId);
        return SUCCESS;
    }

    public String save() throws PublicationAlreadyExistsException {
        if (publicationType.getId() != null) {
            publicationTypeService.updatePublicationType(publicationType.getId(), publicationType.getTitle());
        } else {
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
        if (publicationTypeId == null) {
            addActionError(getText("error.selectPublicationType"));
        }
        if (publicationTypeId != null) {
            List<Publication> publications = publicationService.listPublications()
                    .stream().filter(publication -> publication.getType().getId().equals(publicationTypeId))
                    .collect(Collectors.toList());
            if(!publications.isEmpty()){
                addActionError(getText("error.publicationTypeIsInUse"));
            }
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

