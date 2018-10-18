/**
 * Author: Felix Welter
 */
package de.nordakademie.iaa.library.dao.customer;

public class CustomerAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = -460451166567912547L;

    public CustomerAlreadyExistsException(){
        super("This keyword already exists.");
    }
}