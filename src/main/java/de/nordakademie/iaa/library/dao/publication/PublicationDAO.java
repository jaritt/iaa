package de.nordakademie.iaa.library.dao.publication;

import de.nordakademie.iaa.library.model.Publication;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * This class gives access to publications.
 *
 * @author Felix Welter
 * @see Publication
 */
@Named
public class PublicationDAO {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * List all publications
     *
     * @return list of publications
     */
    public List<Publication> listPublications() {
        return entityManager.createQuery("from Publication", Publication.class).getResultList();
    }


    /**
     * List all publications belonging to the given ids
     *
     * @return list of publications
     */
    public List<Publication> listPublications(List<Long> ids) {
        TypedQuery<Publication> query = entityManager.createQuery("from Publication l where l.id in :ids", Publication.class);
        query.setParameter("ids", ids);
        List<Publication> publications = query.getResultList();
        if (publications.isEmpty()) {
            return null;
        }
        return publications;
    }

    /**
     * Load a specific publication
     *
     * @param publicationId publication identifier
     * @return requested publication
     */
    public Publication loadPublication(Long publicationId) {
        Publication publication = entityManager.find(Publication.class, publicationId);
        if (publication == null) {
            throw new PublicationNotFoundException();
        }
        return publication;
    }

    /**
     * Load a publication identified by its non-technical publication key
     *
     * @param key publication key
     * @return the requested publication
     */
    public Publication findPublicationByKey(String key) {
        TypedQuery<Publication> query = entityManager.createQuery("from Publication l where l.key = :key", Publication.class);
        query.setParameter("key", key);
        List<Publication> publications = query.getResultList();
        if (publications.isEmpty()) {
            return null;
        }
        return publications.get(0);
    }

    /**
     * Delete a specific publication
     *
     * @param publicationId publication identifier
     */
    public void deletePublication(Long publicationId) {
        entityManager.remove(loadPublication(publicationId));
    }

    /**
     * Save a new publication to the database
     *
     * @param publication a new publication
     * @return the persisted publication
     */
    public Publication savePublication(Publication publication) {
        if (findPublicationByKey(publication.getKey()) != null) {
            throw new PublicationAlreadyExistsException();
        }
        entityManager.persist(publication);
        return publication;
    }

    /**
     * Load a publication identified by its ISBN
     *
     * @param isbn International Standard Book Number
     * @return requested publication
     */
    public Publication findPublicationByISBN(String isbn) {
        TypedQuery<Publication> query = entityManager.createQuery("from Publication l where l.isbn = :isbn", Publication.class);
        query.setParameter("isbn", isbn);
        List<Publication> publications = query.getResultList();
        if (publications.isEmpty()) {
            return null;
        }
        return publications.get(0);
    }
}
