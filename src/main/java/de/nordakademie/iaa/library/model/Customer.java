/**
 * Author: Felix Welter
 */
package de.nordakademie.iaa.library.model;

import javax.persistence.*;
import java.util.Objects;

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
