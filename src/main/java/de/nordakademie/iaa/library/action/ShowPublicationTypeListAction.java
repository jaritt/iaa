package de.nordakademie.iaa.library.action;

import com.opensymphony.xwork2.Action;
import de.nordakademie.iaa.library.model.PublicationType;
import de.nordakademie.iaa.library.service.api.PublicationTypeService;

import java.util.List;

public class ShowPublicationTypeListAction implements Action {

    public ShowPublicationTypeListAction(PublicationTypeService publicationTypeService) {
        this.publicationTypeService = publicationTypeService;
    }

    private PublicationTypeService publicationTypeService;

    private List<PublicationType> publicationTypes;

    @Override
    public String execute() throws Exception {
        publicationTypes = publicationTypeService.listPublicationTypes();
        return SUCCESS;
    }

    public List<PublicationType> getPublicationTypes() {
        return publicationTypes;
    }
}
