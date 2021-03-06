package de.nordakademie.iaa.library.dao.customer;

/**
 * @author Felix Welter
 */
public class CustomerAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = -460451166567912547L;

    public CustomerAlreadyExistsException() {
        super("This customer already exists.");
    }
}
