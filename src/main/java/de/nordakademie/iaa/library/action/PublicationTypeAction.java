package de.nordakademie.iaa.library.action;

import com.opensymphony.xwork2.ActionSupport;
import de.nordakademie.iaa.library.model.PublicationType;
import de.nordakademie.iaa.library.service.api.PublicationTypeService;

public class PublicationTypeAction extends ActionSupport {

    private PublicationTypeService publicationTypeService;

    private Long publicatioinTypeId;
    private PublicationType publicationType;

    public String load() throws Exception{
        publicationType = publicationTypeService.loadPublicationType(publicatioinTypeId);
        return SUCCESS;
    }

    public String save() throws Exception{
        if (publicationType.getId() != null){
            publicationTypeService.updatePublicationType(publicationType.getId(), publicationType.getTitle());
        }
        else {
            publicationTypeService.createPublicationType(publicationType);
        }
        return SUCCESS;
    }

    public String delete() throws Exception{
        publicationTypeService.deletePublicationType(publicatioinTypeId);
        return SUCCESS;
    }

    public Long getPublicatioinTypeId() {
        return publicatioinTypeId;
    }

    public void setPublicatioinTypeId(Long publicatioinTypeId) {
        this.publicatioinTypeId = publicatioinTypeId;
    }

    public PublicationType getPublicationType() {
        return publicationType;
    }

    public void setPublicationType(PublicationType publicationType) {
        this.publicationType = publicationType;
    }
}

