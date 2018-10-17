package de.nordakademie.iaa.library.model;

import javax.persistence.Basic;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

/**
 * This represents a lending of a publications.
 *
 * @author Felix Welter
 */
public class Lending {

    public Lending() {
        this.timesProlonged = new Long(0);
    }

    private static final Long MAX_TIMES_PROLONGED = new Long(2);
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

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(optional = false)
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


    /**
     * States if the lending process is finished either
     * by the publication being returned or lost
     *
     * @return If the lending process is finished.
     */
    public boolean isCompleted() {
        return isLost() || isReturned();
    }

    /**
     * States if the lending can be prolonged
     *
     * @return prolongation possible
     */
    public boolean canBeProlonged() {
        return getTimesProlonged() < MAX_TIMES_PROLONGED;
    }

    /**
     * Prolongs this lending until given date
     *
     * @param newEndDate New return date
     */
    public void prolongUntil(LocalDate newEndDate) {
        if (canBeProlonged()) {
            setTimesProlonged(getTimesProlonged() + 1);
            setEndDate(newEndDate);
        } else {
            throw new ProlongationNotPossible();
        }
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
