package de.nordakademie.iaa.library.model;

/**
 * @author Felix Welter
 */
public class ProlongationNotPossible extends RuntimeException {

    private static final long serialVersionUID = -5431951843185821981L;

    public ProlongationNotPossible() {
        super("Lending can not be prolonged.");
    }
}
