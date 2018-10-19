package de.nordakademie.iaa.library.dao.publication;

/**
 * @author Felix Welter
 */
public class PublicationAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = -460451166567912547L;

    public PublicationAlreadyExistsException() {
        super("This publication already exists.");
    }
}
