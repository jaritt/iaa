package de.nordakademie.iaa.library.service;

import de.nordakademie.iaa.library.dao.publication.PublicationDAO;
import de.nordakademie.iaa.library.model.Keyword;
import de.nordakademie.iaa.library.model.Publication;
import de.nordakademie.iaa.library.model.PublicationType;
import de.nordakademie.iaa.library.service.internal.api.PublicationSearchService;
import de.nordakademie.iaa.library.service.api.PublicationService;

import javax.inject.Named;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * Implements functions defined by PublicationService
 *
 * @author Felix Welter
 * @see PublicationService
 */
@Named
public class PublicationServiceImpl implements PublicationService {

    private PublicationDAO dao;

    private PublicationSearchService searchService;

    public PublicationServiceImpl(PublicationDAO dao, PublicationSearchService searchService) {
        this.dao = dao;
        this.searchService = searchService;
    }

    /**
     * Persists a publication
     *
     * @param publication The publication to be persisted.
     */
    @Override
    public void createPublication(Publication publication) {
        dao.savePublication(publication);
        rebuildIndex();
    }

    /**
     * Returns available publications
     *
     * @return List of publications
     */
    @Override
    public List<Publication> listPublications() {
        return dao.listPublications();
    }

    @Override
    public List<Publication> listPublications(List<Long> ids) {
        return dao.listPublications(ids);
    }

    /**
     * Deletes a publication
     *
     * @param id The identifier.
     * @throws EntityNotFoundException
     */
    @Override
    public void deletePublication(Long id) throws EntityNotFoundException {
        Publication publication = loadPublication(id);
        if (publication == null) {
            throw new EntityNotFoundException();
        }
        dao.deletePublication(publication.getId());
        rebuildIndex();
    }

    /**
     * Load a publication
     *
     * @param id The identifier.
     * @return The requested publication
     */
    @Override
    public Publication loadPublication(Long id) {
        return dao.loadPublication(id);
    }


    /**
     * Update a publication
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
    @Override
    public void updatePublication(
            Long id,
            String title,
            String author,
            Long releaseDay,
            Long releaseMonth,
            Long releaseYear,
            String publisher,
            PublicationType type,
            String isbn,
            List<Keyword> keywords,
            Long copies) throws EntityNotFoundException {
        Publication publication = loadPublication(id);
        if (publication == null) {
            throw new EntityNotFoundException();
        }
        publication.setId(id);
        publication.setTitle(title);
        publication.setAuthor(author);
        publication.setReleaseDay(releaseDay);
        publication.setReleaseMonth(releaseMonth);
        publication.setReleaseYear(releaseYear);
        publication.setPublisher(publisher);
        publication.setType(type);
        publication.setIsbn(isbn);
        publication.setKeywords(keywords);
        publication.setCopies(copies);
        rebuildIndex();
    }

    /**
     * Load a publication identified by its ISBN
     *
     * @param isbn International Standard Book Number
     * @return requested publication
     */
    @Override
    public Publication findPublicationByISBN(String isbn) {
        return dao.findPublicationByISBN(isbn);
    }

    /**
     * Load a publication identified by its non-technical publication key
     *
     * @param key publication key
     * @return the requested publication
     */
    @Override
    public Publication findPublicationByKey(String key) {
        return dao.findPublicationByKey(key);
    }

    /**
     * Executes a search for a publication with the
     * given search phrase
     *
     * @param searchPhrase search request
     * @return list of matching publications
     */
    @Override
    public List<Publication> search(String searchPhrase) {
        Publication publication = findPublicationByKey(searchPhrase);
        if (publication != null) return Arrays.asList(publication);
        publication = findPublicationByISBN(searchPhrase);
        if (publication != null) return Arrays.asList(publication);
        return listPublications(searchService.search(searchPhrase));
    }

    /**
     * Executes a search for a publication, searching only
     * the defined fields
     *
     * @param searchPhrase search request
     * @param fields       publication attributes used
     * @return list of matching publications
     */
    @Override
    public List<Publication> search(String searchPhrase, String[] fields) {
        Publication publication = findPublicationByKey(searchPhrase);
        if (publication != null) return Arrays.asList(publication);
        publication = findPublicationByISBN(searchPhrase);
        if (publication != null) return Arrays.asList(publication);
        return listPublications(searchService.search(searchPhrase, fields));
    }

    /**
     * Update the search index
     */
    private void rebuildIndex() {
        searchService.rebuildIndex(listPublications());
    }
}
