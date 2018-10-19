package de.nordakademie.iaa.library.service;

/**
 * @author Felix Welter
 */
public class NoCopyAvailable extends Throwable {

    private static final long serialVersionUID = -6383375285576664202L;

    public NoCopyAvailable() {
        super("No copy of this publication available");
    }
}
