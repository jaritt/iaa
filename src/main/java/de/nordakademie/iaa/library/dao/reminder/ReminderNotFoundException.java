package de.nordakademie.iaa.library.dao.reminder;

/**
 * @author Felix Welter
 */
public class ReminderNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -3744485289761283426L;

    public ReminderNotFoundException(){
        super("The reminder could not be found.");
    }
}
