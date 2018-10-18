package de.nordakademie.iaa.library.dao.lending;

/**
 * @author Felix Welter
 */
public class LendingAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = -460451166567912547L;

    public LendingAlreadyExistsException() {
        super("This lending already exists.");
    }
}
