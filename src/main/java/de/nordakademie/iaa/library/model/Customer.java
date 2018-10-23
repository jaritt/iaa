package de.nordakademie.iaa.library.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * This class represents a customer that is able
 * to lend book from the library, e.g. a student
 * or a professor.
 *
 * @author Felix Welter
 */
@Entity
public class Customer {

    public Customer() {

    }

    public Customer(String firstName, String name, Long matnr) {
        this.name = name;
        this.firstName = firstName;
        this.matnr = matnr;
    }

    public Customer(String firstName, String name) {
        this.name = name;
        this.firstName = firstName;
    }

    public Customer(String name) {
        this.name = name;
    }

    /**
     * Identifier of the customer
     */
    private Long id;

    /**
     * title of the customer
     */
    private String title;

    /**
     * Name of the customer
     */
    private String name;

    /**
     * first name of the customer
     */
    private String firstName;

    /**
     * city of the customer
     */
    private String city;

    /**
     * street of the customer
     */
    private String street;

    /**
     * Student Identifier of the customer (if present)
     */
    private Long matnr;

    /**
     * German post code
     */
    private Long plz;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic(optional = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic
    public Long getMatnr() {
        return matnr;
    }

    public void setMatnr(Long matnr) {
        this.matnr = matnr;
    }

    @Basic
    public Long getPlz() {
        return plz;
    }

    public void setPlz(Long plz) {
        this.plz = plz;
    }

    /**
     * Return a string representation of the customer
     * including the full name and student identifier number
     * for human-readable customer recognition
     *
     * @return
     */
    @Transient
    public String fullRepresentation() {
        StringBuilder builder = new StringBuilder();
        if (getFirstName() != null) builder.append(getFirstName()).append(" ");
        if (getName() != null) builder.append(getName()).append(" ");
        if (getMatnr() != null) builder.append("(").append(getMatnr()).append(")");
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
