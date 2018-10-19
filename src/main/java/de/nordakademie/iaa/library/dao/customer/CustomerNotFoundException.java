package de.nordakademie.iaa.library.dao.customer;

/**
 * @author Felix Welter
 */
public class CustomerNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 3907164798606983597L;

    public CustomerNotFoundException() {
        super("The customer could not be found.");
    }
}
