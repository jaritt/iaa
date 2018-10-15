/**
 * Author: Felix Welter
 */
package de.nordakademie.iaa.library.service.api;

import de.nordakademie.iaa.library.model.Keyword;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface KeywordService {
    /**
     * Takes a new keyword and stores it in the database.
     *
     * @param keyword The keyword to be persisted.
     */
    void createKeyword(Keyword keyword);

    /**
     * List all keywords currently stored in the database.
     *
     * @return a list of Keyword entities. If no keyword was found an empty list is
     * returned.
     */
    @SuppressWarnings("unchecked")
    List<Keyword> listKeywords();

    /**
     * Deletes the keyword with the given id.
     *
     * @param id The identifier.
     * @throws EntityNotFoundException if no keyword could be found for the given id.
     */
    void deleteKeyword(Long id) throws EntityNotFoundException;

    /**
     * Returns the keyword identified by the given id.
     *
     * @param id The identifier.
     * @return the found entity or {@code null} if no entity was found with given identifier.
     */
    Keyword loadKeyword(Long id);

    /**
     * Updates a keyword entity and stores the changes into database.
     *
     * @param id
     * @param word
     * @throws EntityNotFoundException
     */
    void updateKeyword(Long id, String word) throws EntityNotFoundException;

}
