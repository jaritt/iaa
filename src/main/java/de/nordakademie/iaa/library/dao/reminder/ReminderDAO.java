package de.nordakademie.iaa.library.dao.reminder;

import de.nordakademie.iaa.library.model.Lending;
import de.nordakademie.iaa.library.model.Reminder;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * This class gives access to reminders.
 *
 * @author Felix Welter
 * @see Reminder
 */
@Named
public class ReminderDAO {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * List all reminders
     *
     * @return list of reminders
     */
    public List<Reminder> listReminders() {
        return entityManager.createQuery("from Reminder", Reminder.class).getResultList();
    }

    /**
     * Load a specific reminder
     *
     * @param reminderId reminder identifier
     * @return requested reminder
     */
    public Reminder loadReminder(Long reminderId) {
        Reminder reminder = entityManager.find(Reminder.class, reminderId);
        if (reminder == null) {
            throw new ReminderNotFoundException();
        }
        return reminder;
    }

    /**
     * Load reminders belonging to a lending
     *
     * @param lending related lending
     * @return list of reminders
     */
    public List<Reminder> findRemindersByLending(Lending lending) {
        TypedQuery<Reminder> query = entityManager.createQuery("from Reminder l where l.lending = :lending", Reminder.class);
        query.setParameter("lending", lending);
        return query.getResultList();
    }

    /**
     * Remove a reminder from the database
     *
     * @param reminderId reminder identifier
     */
    public void deleteReminder(Long reminderId) {
        entityManager.remove(loadReminder(reminderId));
    }

    /**
     * Persist a new reminder to the database
     *
     * @param reminder a new reminder
     * @return the persisted reminder
     */
    public Reminder saveReminder(Reminder reminder) {
        entityManager.persist(reminder);
        return reminder;
    }
}
