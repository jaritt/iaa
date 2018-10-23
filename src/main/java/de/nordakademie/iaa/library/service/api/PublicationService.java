package de.nordakademie.iaa.library.service.api;

import de.nordakademie.iaa.library.model.Keyword;
import de.nordakademie.iaa.library.model.Publication;
import de.nordakademie.iaa.library.model.PublicationType;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

/**
 * Defines how publication data is accessed
 *
 * @author Felix Welter
 */
public interface PublicationService {
    /**
     * Takes a new publication and stores it in the database.
     *
     * @param publication The publication to be persisted.
     */
    void createPublication(Publication publication);

    /**
     * List all publications currently stored in the database.
     *
     * @return a list of Publication entities. If no publication was found an empty list is
     * returned.
     */
    List<Publication> listPublications();

    /**
     * List all publications currently stored in the database
     * which belong to the given set of publication ids.
     *
     * @return a list of Publication entities. If no publication was found an empty list is
     * returned.
     */
    List<Publication> listPublications(List<Long> ids);

    /**
     * Deletes the publication with the given id.
     *
     * @param id The identifier.
     * @throws EntityNotFoundException if no publication could be found for the given id.
     */
    void deletePublication(Long id) throws EntityNotFoundException;

    /**
     * Returns the publication identified by the given id.
     *
     * @param id The identifier.
     * @return the found entity or {@code null} if no entity was found with given identifier.
     */
    Publication loadPublication(Long id);

    /**
     * Updates a publication entity and stores the changes into database.
     *
     * @param id           The unique identifier
     * @param title        The title of the publication
     * @param author       The author of the publication
     * @param releaseDay   Day of the release date of the publication
     * @param releaseMonth Month of the release date of the publication
     * @param releaseYear  Year of the release date of the publication
     * @param publisher    Publisher
     * @param type         Type of the publication
     * @param isbn         International standard book number
     * @param keywords     Keywords related to the publication
     * @param copies       How many copies are present
     * @throws EntityNotFoundException
     */
    void updatePublication(Long id,
                           String title,
                           String author,
                           Long releaseDay,
                           Long releaseMonth,
                           Long releaseYear,
                           String publisher,
                           PublicationType type,
                           String isbn,
                           List<Keyword> keywords,
                           Long copies) throws EntityNotFoundException;

    /**
     * Loads a publication based on the isbn
     *
     * @param isbn specific ISBN
     * @return requested publication
     */
    Publication findPublicationByISBN(String isbn);

    /**
     * Loads a publication based on the internal
     * library key
     *
     * @param key library key
     * @return requested publication
     */
    Publication findPublicationByKey(String key);

    /**
     * Executes a search for a publication, searching only
     * the defined fields
     *
     * @param searchPhrase search request
     * @param fields       publication attributes used
     * @return list of matching publications
     */
    List<Publication> search(String searchPhrase, String[] fields);

    /**
     * Executes a search for a publication with the
     * given search phrase
     *
     * @param searchPhrase search request
     * @return list of matching publications
     */
    List<Publication> search(String searchPhrase);

}
