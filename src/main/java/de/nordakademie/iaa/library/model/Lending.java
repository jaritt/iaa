package de.nordakademie.iaa.library.model;

import net.bytebuddy.asm.Advice;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * This represents a lending of a publication and holds
 * information about lending timing und the customer.
 * Lending is aware of its reminders, if a new reminder is
 * due and if the lending process is completed by return
 * of the publication or loss.
 *
 * @author Felix Welter
 */
@Entity
public class Lending {

    private static final int REMINDER_TIME_INTERVAL = 7;
    private static final int MAX_TIMES_PROLONGED = 2;

    public Lending() {
        this.timesProlonged = new Long(0);
    }

    /**
     * Unique identifier
     */
    private Long id;

    /**
     * Publication which was borrowed
     */
    private Publication publication;

    /**
     * The customer who received the publication
     */
    private Customer customer;

    /**
     * Date when the customer received the publication
     * and the lending started
     */
    private LocalDate startDate;

    /**
     * When the customer needs the return the
     * publication a the latest
     */
    private LocalDate endDate;

    /**
     * States if the customer has returned
     * the publication
     */
    private boolean returned;

    /**
     * States if the book was lost
     */
    private boolean lost;

    /**
     * How many times this lending was prolonged
     */
    private Long timesProlonged;

    /**
     * Reminder that have been sent for this lending
     */
    private List<Reminder> reminders;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(optional = false)
    @LazyCollection(LazyCollectionOption.FALSE)
    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    @ManyToOne(optional = false)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Basic
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Basic
    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Basic
    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    @Basic
    public boolean isLost() {
        return lost;
    }

    public void setLost(boolean lost) {
        this.lost = lost;
    }

    @Basic
    public Long getTimesProlonged() {
        return timesProlonged;
    }

    public void setTimesProlonged(Long timesProlonged) {
        this.timesProlonged = timesProlonged;
    }

    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Reminder> getReminders() {
        return reminders;
    }

    public void setReminders(List<Reminder> reminders) {
        this.reminders = reminders;
    }

    /**
     * Helper to add a reminder to a
     * lending bidirectional
     *
     * @param reminder the reminder to be added
     */
    public void addReminder(Reminder reminder) {
        this.reminders.add(reminder);
        reminder.setLending(this);
    }

    /**
     * States if the lending process is finished either
     * by the publication being returned or lost
     *
     * @return If the lending process is finished.
     */
    @Transient
    public boolean isCompleted() {
        return isLost() || isReturned();
    }

    /**
     * States if the lending can be prolonged
     *
     * @return prolongation possible
     */
    @Transient
    public boolean canBeProlonged() {
        return getTimesProlonged() < MAX_TIMES_PROLONGED;
    }

    /**
     * Prolongs this lending until given date
     *
     * @param newEndDate New return date
     */
    @Transient
    public void prolongUntil(LocalDate newEndDate) {
        if (canBeProlonged()) {
            setTimesProlonged(getTimesProlonged() + 1);
            setEndDate(newEndDate);
        } else {
            throw new ProlongationNotPossible();
        }
    }

    /**
     * States if this lending has active reminders.
     * That would mean that reminders have been sent
     * and the publication was neither returned
     * nor marked as lost
     *
     * @return States if a reminder process is open
     */
    @Transient
    public boolean hasActiveReminder() {
        return !reminders.isEmpty() && !isCompleted();
    }

    /**
     * States if this lending can be marked as lost
     *
     * @return If lending can be marked as lost
     */
    @Transient
    public boolean isLossPossible() {
        return reminders.size() >= 3 && !isCompleted();
    }

    /**
     * States if a reminder is due to sent
     *
     * @return If a reminder is due
     */
    @Transient
    public boolean isReminderDue() {
        if (isCompleted()) return false;

        return isOverDue()
                && (getReminders().isEmpty()
                || ((DAYS.between(getLastReminder().getDate(), LocalDate.now()) >= REMINDER_TIME_INTERVAL)));
    }

    /**
     * States if the lending should have been
     * returned by now
     *
     * @return if return is due
     */
    @Transient
    public boolean isOverDue() {
        return LocalDate.now().isAfter(getEndDate()) && !isCompleted();
    }

    /**
     * Returns the last reminder that was sent
     * for this publication
     *
     * @return Last reminder that was sent
     */
    @Transient
    public Reminder getLastReminder() {
        return reminders.get(reminders.size() - 1);
    }

    /**
     * Return the title of the related publication
     *
     * @return title of the related publiation
     */
    @Transient
    public String getPublicationTitle() {
        return getPublication().getTitle();
    }

    @Transient
    public Long getPublicationId() {
        return getPublication().getId();
    }

    @Transient
    public Long getCustomerId() {
        return getCustomer().getId();
    }

    @Transient
    public String getPublicationKey() {
        return getPublication().getKey();
    }

    @Transient
    public String getCustomerFullName() {
        String customerFullName = getCustomer().getFirstName() + " " + getCustomer().getName();
        return customerFullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lending lending = (Lending) o;
        return Objects.equals(id, lending.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
