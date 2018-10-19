package de.nordakademie.iaa.library.dao.publication;

/**
 * Author: Felix Welter
 */
public class PublicationNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 3907164798606983597L;

    public PublicationNotFoundException() {
        super("The Keyword could not be found.");
    }
}
