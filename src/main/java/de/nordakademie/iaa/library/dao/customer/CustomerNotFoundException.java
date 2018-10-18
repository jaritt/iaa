/**
 * Author: Felix Welter
 */
package de.nordakademie.iaa.library.dao.customer;

public class CustomerNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 3907164798606983597L;

    public CustomerNotFoundException() {
        super("The Keyword could not be found.");
    }
}
