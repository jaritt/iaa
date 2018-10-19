package de.nordakademie.iaa.library.dao.reminder;

/**
 * @author Felix Welter
 */
public class ReminderAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = -460451166567912547L;

    public ReminderAlreadyExistsException() {
        super("This reminder already exists.");
    }
}
