package de.nordakademie.iaa.library.action;

import com.opensymphony.xwork2.Action;
import de.nordakademie.iaa.library.model.Publication;
import de.nordakademie.iaa.library.service.api.PublicationService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public class ShowPublicationListAction implements Action{

    public ShowPublicationListAction(PublicationService publicationService) {

        this.publicationService = publicationService;
    }

    private PublicationService publicationService;

    private List<Publication> publications;

    @Override
    public String execute() throws EntityNotFoundException {
        publications = publicationService.listPublications();
        return SUCCESS;
    }

    public List<Publication> getPublications() {return publications;}
}
