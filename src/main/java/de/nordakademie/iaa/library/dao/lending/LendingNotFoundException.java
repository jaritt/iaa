package de.nordakademie.iaa.library.dao.lending;

/**
 * @author Felix Welter
 */
public class LendingNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 3907164798606983597L;

    public LendingNotFoundException(){
        super("The lending could not be found.");
    }
}
