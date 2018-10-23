package de.nordakademie.iaa.library.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

/**
 * This represents a reminder that is sent to
 * a customer that did not return a publication
 * until the return date.
 *
 * @author Felix Welter
 */
@Entity
public class Reminder {

    /**
     * Unique identifier
     */
    private Long id;

    /**
     * Date when the reminder was sent
     */
    private LocalDate date;

    /**
     * Which lending this reminder belongs to
     */
    private Lending lending;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @ManyToOne
    @LazyCollection(LazyCollectionOption.FALSE)
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
