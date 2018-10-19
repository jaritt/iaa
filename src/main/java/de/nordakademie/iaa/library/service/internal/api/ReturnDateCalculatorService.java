package de.nordakademie.iaa.library.service.internal.api;

import de.nordakademie.iaa.library.model.Customer;
import de.nordakademie.iaa.library.model.Lending;
import de.nordakademie.iaa.library.model.Publication;

import javax.swing.text.DefaultEditorKit;
import java.time.LocalDate;

/**
 * Calculates a return date. Lending, customer and publication
 * can given as input. However all setter methods are optional.
 * The Calculator will return the best return date based on inputs
 * given.
 *
 * @author Felix Welter
 */
public interface ReturnDateCalculatorService {

    /**
     * Reset all input that was given to the calculator
     */
    ReturnDateCalculatorService reset();

    /**
     * Set the relevant lending.
     *
     * @param lending Lending
     * @return the service itself
     */
    ReturnDateCalculatorService setLending(Lending lending);

    /**
     * Set the relevant customer
     *
     * @param customer Customer
     * @return the service itself
     */
    ReturnDateCalculatorService setCustomer(Customer customer);

    /**
     * Set the relevant publication
     *
     * @param publication Publication
     * @return the service itself
     */
    ReturnDateCalculatorService setPublication(Publication publication);

    /**
     * Get the return date, based on all previously set inputs.
     *
     * @return the return date
     */
    LocalDate getReturnDate();
}
