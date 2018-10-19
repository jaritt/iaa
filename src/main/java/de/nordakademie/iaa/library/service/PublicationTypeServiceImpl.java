package de.nordakademie.iaa.library.service;

import de.nordakademie.iaa.library.dao.publicationtype.PublicationTypeDAO;
import de.nordakademie.iaa.library.model.PublicationType;
import de.nordakademie.iaa.library.service.api.PublicationTypeService;

import javax.inject.Named;
import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Implements functions defined by PublicationTypeService
 *
 * @author Felix Welter
 * @see PublicationTypeService
 */
@Named
public class PublicationTypeServiceImpl implements PublicationTypeService {

    private PublicationTypeDAO dao;

    public PublicationTypeServiceImpl(PublicationTypeDAO dao) {
        this.dao = dao;
    }

    /**
     * @param publicationType The publicationType to be persisted.
     */
    @Override
    public void createPublicationType(PublicationType publicationType) {
        dao.savePublicationType(publicationType);
    }

    /**
     * Returns available publicationTypes
     *
     * @return List of publicationTypes
     */
    @Override
    public List<PublicationType> listPublicationTypes() {
        return dao.listPublicationTypes();
    }

    /**
     * Deletes a publicationType
     *
     * @param id The identifier.
     * @throws EntityNotFoundException
     */
    @Override
    public void deletePublicationType(Long id) throws EntityNotFoundException {
        PublicationType publicationType = loadPublicationType(id);
        if (publicationType == null) {
            throw new EntityNotFoundException();
        }
        dao.deletePublicationType(publicationType.getId());
    }

    /**
     * Load a publicationType
     *
     * @param id The identifier.
     * @return The requested publicationType
     */
    @Override
    public PublicationType loadPublicationType(Long id) {
        return dao.loadPublicationType(id);
    }

    /**
     * Update a publicationType
     *
     * @param id
     * @param title
     * @throws EntityNotFoundException
     */
    @Override
    public void updatePublicationType(Long id, String title) throws EntityNotFoundException {
        PublicationType publicationType = loadPublicationType(id);
        if (publicationType == null) {
            throw new EntityNotFoundException();
        }
        publicationType.setTitle(title);
    }
}
