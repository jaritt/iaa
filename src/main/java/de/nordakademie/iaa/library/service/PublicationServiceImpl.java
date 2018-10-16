/**
 * Author: Felix Welter
 */
package de.nordakademie.iaa.library.service;

import de.nordakademie.iaa.library.dao.PublicationDAO;
import de.nordakademie.iaa.library.model.Keyword;
import de.nordakademie.iaa.library.model.Publication;
import de.nordakademie.iaa.library.model.PublicationType;
import de.nordakademie.iaa.library.service.api.PublicationService;

import javax.inject.Named;
import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

@Named
public class PublicationServiceImpl implements PublicationService {

    private PublicationDAO dao;

    public PublicationServiceImpl(PublicationDAO dao) {
        this.dao = dao;
    }

    /**
     * @param publication The publication to be persisted.
     */
    @Override
    public void createPublication(Publication publication) {
        dao.savePublication(publication);
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
     * @param id          The unique identifier
     * @param title       The title of the publication
     * @param author      The author of the publication
     * @param releaseDate Release date of the publication
     * @param publisher   Publisher
     * @param type        Type of the publication
     * @param isbn        International standard book number
     * @param keywords    Keywords related to the publication
     * @param copies      How many copies are present
     * @throws EntityNotFoundException
     */
    @Override
    public void updatePublication(Long id, String title, String author, Date releaseDate, String publisher, PublicationType type, String isbn, List<Keyword> keywords, Long copies) throws EntityNotFoundException {
        Publication publication = loadPublication(id);
        if (publication == null) {
            throw new EntityNotFoundException();
        }
        publication.setId(id);
        publication.setTitle(title);
        publication.setAuthor(author);
        publication.setReleaseDate(releaseDate);
        publication.setPublisher(publisher);
        publication.setType(type);
        publication.setIsbn(isbn);
        publication.setKeywords(keywords);
        publication.setCopies(copies);
    }
}
