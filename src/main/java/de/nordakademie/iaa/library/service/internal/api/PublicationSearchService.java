package de.nordakademie.iaa.library.service.internal.api;

import de.nordakademie.iaa.library.model.Keyword;
import de.nordakademie.iaa.library.model.Publication;
import de.nordakademie.iaa.library.model.PublicationType;

import java.time.LocalDate;
import java.util.List;

public interface PublicationSearchService {

    /**
     * Executes a search for a publication with the
     * given search phrase
     *
     * @param searchPhrase search request
     * @return list of matching publications
     */
    List<Long> search(String searchPhrase);

    /**
     * Executes a search for a publication, searching only
     * the defined fields
     *
     * @param searchPhrase search request
     * @param fields       publication attributes used
     * @return list of matching publications
     */
    List<Long> search(String searchPhrase, String[] fields);

    /**
     * Rebuilds the index from current publications.
     * Should be called after creating, updating or
     * deleting a publication
     *
     * @param publications publications for the new index
     */
    void rebuildIndex(List<Publication> publications);
}
