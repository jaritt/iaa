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

    public List<Reminder> listReminders() {
        return entityManager.createQuery("from Reminder", Reminder.class).getResultList();
    }

    public Reminder loadReminder(Long reminderId) {
        Reminder reminder = (Reminder) entityManager.find(Reminder.class, reminderId);
        if (reminder == null) {
            throw new ReminderNotFoundException();
        }
        return reminder;
    }

    public List<Reminder> findRemindersByLending(Lending lending) {
        TypedQuery<Reminder> query = entityManager.createQuery("from Reminder l where l.lending = :lending", Reminder.class);
        query.setParameter("lending", lending);
        return query.getResultList();
    }

    public void deleteReminder(Long reminderId) {
        entityManager.remove(loadReminder(reminderId));
    }

    public Reminder saveReminder(Reminder reminder) {
        entityManager.persist(reminder);
        return reminder;
    }
}
