package de.nordakademie.iaa.library.dao.keyword;

/**
 * @author Felix Welter
 */
public class KeywordAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = -460451166567912547L;

    public KeywordAlreadyExistsException() {
        super("This keyword already exists.");
    }
}
