/**
 * Author: Felix Welter
 */
package de.nordakademie.iaa.library.service;

import de.nordakademie.iaa.library.dao.reminder.ReminderDAO;
import de.nordakademie.iaa.library.model.Lending;
import de.nordakademie.iaa.library.model.Reminder;
import de.nordakademie.iaa.library.service.api.LendingService;
import de.nordakademie.iaa.library.service.api.ReminderService;
import de.nordakademie.iaa.library.service.internal.api.NotifierService;

import javax.inject.Named;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

@Named
public class ReminderServiceImpl implements ReminderService {

    private ReminderDAO dao;

    private LendingService lendingService;

    private NotifierService notifier;

    public ReminderServiceImpl(ReminderDAO dao, LendingService lendingService, NotifierService notifier) {
        this.dao = dao;
        this.lendingService = lendingService;
        this.notifier = notifier;
    }

    /**
     * @param reminder The reminder to be persisted.
     */
    @Override
    public void createReminder(Reminder reminder) {
        dao.saveReminder(reminder);
    }

    /**
     * Returns available reminders
     *
     * @return List of reminders
     */
    @Override
    public List<Reminder> listReminders() {
        return dao.listReminders();
    }

    /**
     * Deletes a reminder
     *
     * @param id The identifier.
     * @throws EntityNotFoundException
     */
    @Override
    public void deleteReminder(Long id) throws EntityNotFoundException {
        Reminder reminder = loadReminder(id);
        if (reminder == null) {
            throw new EntityNotFoundException();
        }
        dao.deleteReminder(reminder.getId());
    }

    /**
     * Load a reminder
     *
     * @param id The identifier.
     * @return The requested reminder
     */
    @Override
    public Reminder loadReminder(Long id) {
        return dao.loadReminder(id);
    }

    /**
     * Update a reminder
     *
     * @param id
     * @param date
     * @param lending
     * @throws EntityNotFoundException
     */
    @Override
    public void updateReminder(Long id, LocalDate date, Lending lending) throws EntityNotFoundException {
        Reminder reminder = loadReminder(id);
        if (reminder == null) {
            throw new EntityNotFoundException();
        }
        reminder.setDate(date);
        reminder.setLending(lending);
    }

    /**
     * Send a reminder about a certain lending
     *
     * @param lending Lending which is over due
     */
    @Override
    public void sendReminder(Lending lending) {
        notifier.sendMessage(lending.getCustomer(), getReminderMessage(lending));
        Reminder reminder = new Reminder();
        reminder.setDate(LocalDate.now());
        lending.addReminder(reminder);
        createReminder(reminder);
    }

    private String getReminderMessage(Lending lending) {
        return "Please return your publication " + lending.getPublicationTitle();
    }

    /**
     * Finds reminders belonging to a lending
     *
     * @param lending Lending
     * @return List of reminders
     */
    @Override
    public List<Reminder> findRemindersByLending(Lending lending) {
        return dao.findRemindersByLending(lending);
    }
}
