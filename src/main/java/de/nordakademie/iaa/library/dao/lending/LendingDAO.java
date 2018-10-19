package de.nordakademie.iaa.library.dao.lending;

import de.nordakademie.iaa.library.model.Lending;
import de.nordakademie.iaa.library.model.Publication;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * This class gives access to publication types.
 *
 * @author Felix Welter
 * @see Lending
 */
@Named
public class LendingDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Lending> listLendings() {
        return entityManager.createQuery("from Lending", Lending.class).getResultList();
    }

    public Lending loadLending(Long lendingId) {
        Lending lending = entityManager.find(Lending.class, lendingId);
        if (lending == null) {
            throw new LendingNotFoundException();
        }
        return lending;
    }

    public List<Lending> findLendingsByPublication(Publication publication) {
        TypedQuery<Lending> query = entityManager.createQuery("from Lending l where l.publication = :publication", Lending.class);
        query.setParameter("publication", publication);
        return query.getResultList();
    }

    public void deleteLending(Long lendingId) {
        entityManager.remove(loadLending(lendingId));
    }

    public Lending saveLending(Lending lending) {
        entityManager.persist(lending);
        return lending;
    }
}
