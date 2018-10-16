/**
 * Author: Felix Welter
 */
package de.nordakademie.iaa.library.dao;

import de.nordakademie.iaa.library.model.PublicationType;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Named
public class PublicationTypeDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<PublicationType> listPublicationTypes() {
        return entityManager.createQuery("from PublicationType", PublicationType.class).getResultList();
    }

    public PublicationType loadPublicationType(Long publicationTypeId) {
        PublicationType publicationType = (PublicationType) entityManager.find(PublicationType.class, publicationTypeId);
        if (publicationType == null) {
            throw new PublicationTypeNotFoundException();
        }
        return publicationType;
    }

    public PublicationType findPublicationTypeByTitle(String title) {
        TypedQuery<PublicationType> query = entityManager.createQuery("from PublicationType l where l.title = :title", PublicationType.class);
        query.setParameter("title", title);
        List<PublicationType> publicationTypes = query.getResultList();
        if (publicationTypes.isEmpty()) {
            return null;
        }
        return publicationTypes.get(0);
    }

    public void deletePublicationType(Long publicationTypeId) {
        entityManager.remove(loadPublicationType(publicationTypeId));
    }

    public PublicationType savePublicationType(PublicationType publicationType) {
        if (findPublicationTypeByTitle(publicationType.getTitle()) != null){
            throw new PublicationTypeAlreadyExistsException();
        }
        entityManager.persist(publicationType);
        return publicationType;
    }
}
