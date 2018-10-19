package de.nordakademie.iaa.library.dao.publicationtype;

import de.nordakademie.iaa.library.model.PublicationType;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * This class gives access to publication types.
 *
 * @author Felix Welter
 * @see PublicationType
 */
@Named
public class PublicationTypeDAO {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * List all publication types
     *
     * @return list of publication types
     */
    public List<PublicationType> listPublicationTypes() {
        return entityManager.createQuery("from PublicationType", PublicationType.class).getResultList();
    }

    /**
     * Load a specific publication type
     *
     * @param publicationTypeId publication type identifier
     * @return requested publication type
     */
    public PublicationType loadPublicationType(Long publicationTypeId) {
        PublicationType publicationType = entityManager.find(PublicationType.class, publicationTypeId);
        if (publicationType == null) {
            throw new PublicationTypeNotFoundException();
        }
        return publicationType;
    }

    /**
     * Load a publication type by title
     *
     * @param title publication type title
     * @return the requested publication type
     */
    public PublicationType findPublicationTypeByTitle(String title) {
        TypedQuery<PublicationType> query = entityManager.createQuery("from PublicationType l where l.title = :title", PublicationType.class);
        query.setParameter("title", title);
        List<PublicationType> publicationTypes = query.getResultList();
        if (publicationTypes.isEmpty()) {
            return null;
        }
        return publicationTypes.get(0);
    }

    /**
     * Delete a specific publication type
     *
     * @param publicationTypeId publication type identifier
     */
    public void deletePublicationType(Long publicationTypeId) {
        entityManager.remove(loadPublicationType(publicationTypeId));
    }

    /**
     * Persist a new publication type
     *
     * @param publicationType a new publication type
     * @return the persisted publication type
     */
    public PublicationType savePublicationType(PublicationType publicationType) {
        if (findPublicationTypeByTitle(publicationType.getTitle()) != null) {
            throw new PublicationTypeAlreadyExistsException();
        }
        entityManager.persist(publicationType);
        return publicationType;
    }
}
