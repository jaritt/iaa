package de.nordakademie.iaa.library.service.api;

import de.nordakademie.iaa.library.model.Lending;
import de.nordakademie.iaa.library.model.Reminder;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Felix Welter
 */
public interface ReminderService {
    /**
     * Takes a new reminder and stores it in the database.
     *
     * @param reminder The reminder to be persisted.
     */
    void createReminder(Reminder reminder);

    /**
     * List all reminders currently stored in the database.
     *
     * @return a list of Reminder entities. If no reminder was found an empty list is
     * returned.
     */
    @SuppressWarnings("unchecked")
    List<Reminder> listReminders();

    /**
     * Deletes the reminder with the given id.
     *
     * @param id The identifier.
     * @throws EntityNotFoundException if no reminder could be found for the given id.
     */
    void deleteReminder(Long id) throws EntityNotFoundException;

    /**
     * Returns the reminder identified by the given id.
     *
     * @param id The identifier.
     * @return the found entity or {@code null} if no entity was found with given identifier.
     */
    Reminder loadReminder(Long id);

    /**
     * Updates a reminder entity and stores the changes into database.
     *
     * @param id
     * @param date
     * @param lending
     * @throws EntityNotFoundException
     */
    void updateReminder(Long id, LocalDate date, Lending lending) throws EntityNotFoundException;

    /**
     * Send a reminder about a certain lending
     *
     * @param lending
     */
    void sendReminder(Lending lending);

    /**
     * Find a reminder for a specific lending
     *
     * @param lending
     * @return
     */
    List<Reminder> findRemindersByLending(Lending lending);
}
