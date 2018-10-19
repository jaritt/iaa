/**
 * Author: Felix Welter
 */
package de.nordakademie.iaa.library.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * This class indicates which type a publication is of.
 *
 * @author Feli Welter
 */
@Entity
public class PublicationType {

    public PublicationType() {

    }

    public PublicationType(String title) {
        this.title = title;
    }

    /**
     * The identifier
     */
    private Long id;

    /**
     * Designation of the Publication Type
     */
    private String title;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublicationType that = (PublicationType) o;
        return Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
