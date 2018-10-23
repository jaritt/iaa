package de.nordakademie.iaa.library.service;

/**
 * @author Felix Welter
 */
public class SearchFailedException extends RuntimeException {

    private static final long serialVersionUID = -5602273949380444745L;

    public SearchFailedException() {
        super("Error during search. Make sure the index directory is writable and the " +
                "search query is all right.");
    }
}
