package de.nordakademie.iaa.library.model;

import javax.persistence.Basic;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Objects;

/**
 * This represents a reminder that is sent to
 * a customer that did not return a publication
 * until the return date.
 *
 * @author Felix Welter
 */
public class Reminder {

    /**
     * Unique identifier
     */
    private Long id;

    /**
     * Date when the reminder was sent
     */
    private Long LocalDate;

    /**
     * Which lending this reminder belongs to
     */
    private Lending lending;

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    public Long getLocalDate() {
        return LocalDate;
    }

    public void setLocalDate(Long localDate) {
        LocalDate = localDate;
    }

    @ManyToOne
    public Lending getLending() {
        return lending;
    }

    public void setLending(Lending lending) {
        this.lending = lending;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reminder reminder = (Reminder) o;
        return Objects.equals(id, reminder.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
