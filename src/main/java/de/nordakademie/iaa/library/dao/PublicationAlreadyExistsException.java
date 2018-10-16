/**
 * Author: Felix Welter
 */
package de.nordakademie.iaa.library.dao;

public class PublicationAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = -460451166567912547L;

    public PublicationAlreadyExistsException(){
        super("This keyword already exists.");
    }
}
