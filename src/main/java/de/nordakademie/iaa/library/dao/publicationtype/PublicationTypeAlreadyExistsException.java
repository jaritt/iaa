package de.nordakademie.iaa.library.dao.publicationtype;

/**
 * Author: Felix Welter
 */
public class PublicationTypeAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = 6017669862268040702L;

    public PublicationTypeAlreadyExistsException() {
        super("This publication type already exists.");
    }
}
