/**
 * Author: Felix Welter
 */
package de.nordakademie.iaa.library.dao.publicationtype;

public class PublicationTypeNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -6965142737973275409L;

    public PublicationTypeNotFoundException(){
        super("The publication type could not be found.");
    }
}