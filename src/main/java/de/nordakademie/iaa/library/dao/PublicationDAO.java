/**
 * Author: Felix Welter
 */
package de.nordakademie.iaa.library.dao;

import de.nordakademie.iaa.library.model.Publication;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Named
public class PublicationDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Publication> listPublications() {
        return entityManager.createQuery("from Publication", Publication.class).getResultList();
    }

    public Publication loadPublication(Long publiationId) {
        Publication publiation = (Publication) entityManager.find(Publication.class, publiationId);
        if (publiation == null) {
            throw new PublicationNotFoundException();
        }
        return publiation;
    }

    public Publication findPublicationByWord(String key) {
        TypedQuery<Publication> query = entityManager.createQuery("from Publication l where l.key = :key", Publication.class);
        query.setParameter("key", key);
        List<Publication> publiations = query.getResultList();
        if (publiations.isEmpty()) {
            return null;
        }
        return publiations.get(0);
    }

    public void deletePublication(Long publiationId) {
        entityManager.remove(loadPublication(publiationId));
    }

    public Publication savePublication(Publication publiation) {
        if (findPublicationByWord(publiation.getKey()) != null) {
            throw new PublicationAlreadyExistsException();
        }
        entityManager.persist(publiation);
        return publiation;
    }
}
