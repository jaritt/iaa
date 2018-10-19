package de.nordakademie.iaa.library.service.api;

import de.nordakademie.iaa.library.model.PublicationType;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Author: Felix Welter
 */
public interface PublicationTypeService {
    /**
     * Takes a new publication type and stores it in the database.
     *
     * @param publicationType The publication type to be persisted.
     */
    void createPublicationType(PublicationType publicationType);

    /**
     * List all publication types currently stored in the database.
     *
     * @return a list of PublicationType entities. If no publication type was found an empty list is
     * returned.
     */
    @SuppressWarnings("unchecked")
    List<PublicationType> listPublicationTypes();

    /**
     * Deletes the publication type with the given id.
     *
     * @param id The identifier.
     * @throws EntityNotFoundException if no publication type could be found for the given id.
     */
    void deletePublicationType(Long id) throws EntityNotFoundException;

    /**
     * Returns the publication type identified by the given id.
     *
     * @param id The identifier.
     * @return the found entity or {@code null} if no entity was found with given identifier.
     */
    PublicationType loadPublicationType(Long id);

    /**
     * Updates a publication type entity and stores the changes into database.
     *
     * @param id
     * @param title
     * @throws EntityNotFoundException
     */
    void updatePublicationType(Long id, String title) throws EntityNotFoundException;

    /**
     * Finds a keyword identified by the word itself
     *
     * @param title The word to search for
     * @return The keyword that is searched for
     */
    PublicationType findPublicationTypeByTitle(String title);
}
