package de.nordakademie.iaa.library.service.api;

import de.nordakademie.iaa.library.model.Customer;
import de.nordakademie.iaa.library.model.Lending;
import de.nordakademie.iaa.library.model.ProlongationNotPossible;
import de.nordakademie.iaa.library.model.Publication;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * @author Felix Welter
 */
public interface LendingService {

    /**
     * Finds a lendings given by its publication
     *
     * @param publication Publication
     * @return Lendings
     */
    List<Lending> findLendingsByPublication(Publication publication);

    /**
     * Creates a lending for a customer and a publicatin
     *
     * @param publication Publication that is borrowed
     * @param customer    Customer hat receives the publication
     * @return The created lending
     */
    Lending lendPublication(Publication publication, Customer customer);

    /**
     * This prolongs a lending. New duration is calculated automatically.
     * Check with lending.canBeProlonged() if the lending can be prolonged
     * before using this method or catch ProlongationNotPossible
     *
     * @param lending The lending to be prolonged
     * @throws ProlongationNotPossible Thrown if lending cant be prolonged
     */
    void prolongLending(Lending lending) throws ProlongationNotPossible;

    /**
     * Use this to mark a lending as returned and make that copy of
     * the publication available again. The lending process is then completed.
     *
     * @param lending Lending to be marked as returned
     */
    void markLendingAsReturned(Lending lending);

    /**
     * Use to mark this lending / copy of the publication as lost.
     * Check if a a loss ist justified by calling lending.isLossPossible().
     * The Lending will be marked as a loss without further checking.
     *
     * @param lending Lending to be marked as lost
     */
    void markLendingAsLost(Lending lending);

    /**
     * List all lendings currently stored in the database.
     *
     * @return a list of Lending entities. If no lending was found an empty list is
     * returned.
     */
    @SuppressWarnings("unchecked")
    List<Lending> listLendings();

    /**
     * Returns the lending identified by the given id.
     *
     * @param id The identifier.
     * @return the found entity or {@code null} if no entity was found with given identifier.
     */
    Lending loadLending(Long id);
}
