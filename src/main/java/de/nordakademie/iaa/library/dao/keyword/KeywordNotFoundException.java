package de.nordakademie.iaa.library.dao.keyword;

/**
 * @author Felix Welter
 */
public class KeywordNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 3907164798606983597L;

    public KeywordNotFoundException() {
        super("The Keyword could not be found.");
    }
}
