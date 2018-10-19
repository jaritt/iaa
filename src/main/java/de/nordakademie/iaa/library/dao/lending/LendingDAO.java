package de.nordakademie.iaa.library.dao.lending;

import de.nordakademie.iaa.library.model.Lending;
import de.nordakademie.iaa.library.model.Publication;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * This class gives access to lendings.
 *
 * @author Felix Welter
 * @see Lending
 */
@Named
public class LendingDAO {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * List all lendings
     *
     * @return list of lendings
     */
    public List<Lending> listLendings() {
        return entityManager.createQuery("from Lending", Lending.class).getResultList();
    }

    /**
     * Load a specific lending
     *
     * @param lendingId lending identifier
     * @return requested lending
     */
    public Lending loadLending(Long lendingId) {
        Lending lending = entityManager.find(Lending.class, lendingId);
        if (lending == null) {
            throw new LendingNotFoundException();
        }
        return lending;
    }

    /**
     * Load lendings belonging to a specific publication
     *
     * @param publication related publication
     * @return list of lendings
     */
    public List<Lending> findLendingsByPublication(Publication publication) {
        TypedQuery<Lending> query = entityManager.createQuery("from Lending l where l.publication = :publication", Lending.class);
        query.setParameter("publication", publication);
        return query.getResultList();
    }

    /**
     * Delete a landing from the database
     *
     * @param lendingId lending identifier
     */
    public void deleteLending(Long lendingId) {
        entityManager.remove(loadLending(lendingId));
    }

    /**
     * Persist a given lending to the database
     *
     * @param lending a new lending
     * @return the persisted lending
     */
    public Lending saveLending(Lending lending) {
        entityManager.persist(lending);
        return lending;
    }
}
