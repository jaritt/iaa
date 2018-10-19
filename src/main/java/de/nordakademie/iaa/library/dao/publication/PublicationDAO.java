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

    public List<Publication> listPublications() {
        return entityManager.createQuery("from Publication", Publication.class).getResultList();
    }

    public Publication loadPublication(Long publicationId) {
        Publication publication = entityManager.find(Publication.class, publicationId);
        if (publication == null) {
            throw new PublicationNotFoundException();
        }
        return publication;
    }

    public Publication findPublicationByKey(String key) {
        TypedQuery<Publication> query = entityManager.createQuery("from Publication l where l.key = :key", Publication.class);
        query.setParameter("key", key);
        List<Publication> publications = query.getResultList();
        if (publications.isEmpty()) {
            return null;
        }
        return publications.get(0);
    }

    public void deletePublication(Long publicationId) {
        entityManager.remove(loadPublication(publicationId));
    }

    public Publication savePublication(Publication publication) {
        if (findPublicationByKey(publication.getKey()) != null) {
            throw new PublicationAlreadyExistsException();
        }
        entityManager.persist(publication);
        return publication;
    }
}
