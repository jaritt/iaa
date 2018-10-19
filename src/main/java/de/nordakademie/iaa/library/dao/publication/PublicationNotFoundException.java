package de.nordakademie.iaa.library.dao.publication;

/**
 * @author Felix Welter
 */
public class PublicationNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 3907164798606983597L;

    public PublicationNotFoundException() {
        super("The publication could not be found.");
    }
}
