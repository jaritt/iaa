
package de.nordakademie.iaa.library.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Represents a publication which is owned by the library.
 *
 * @author Felix Welter
 */
@Entity
public class Publication {

    /**
     * Identifier
     */
    private Long id;

    /**
     * Internal publication key
     */
    private String key;

    /**
     * Title of the publication
     */
    private String title;

    /**
     * Author of the publication
     */
    private String author;

    /**
     * Release date
     */
    private LocalDate releaseDate;

    /**
     * Publisher
     */
    private String publisher;

    /**
     * Publication Type
     */
    private PublicationType type;

    /**
     * ISBN
     */
    private String isbn;

    /**
     * Keywords belonging to the publication
     */
    private List<Keyword> keywords;

    /**
     * Count of copies
     */
    private Long copies;

    /**
     * Lendings of this publication
     */
    private List<Lending> lendings;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NaturalId
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Basic
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Basic
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Basic
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @ManyToOne(optional = false)
    public PublicationType getType() {
        return type;
    }

    public void setType(PublicationType type) {
        this.type = type;
    }

    @Basic
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "publication_keyword",
            joinColumns = @JoinColumn(name = "publication_id"),
            inverseJoinColumns = @JoinColumn(name = "keyword_id")
    )
    public List<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }

    @Basic
    public Long getCopies() {
        return copies;
    }

    public void setCopies(Long copies) {
        this.copies = copies;
    }

    @OneToMany
    public List<Lending> getLendings() {
        return lendings;
    }

    public void setLendings(List<Lending> lendings) {
        this.lendings = lendings;
    }

    public void addLending(Lending lending) {
        this.lendings.add(lending);
        lending.setPublication(this);
    }

    /**
     * States how many copies of this publication
     * are available and could be lend right now.
     *
     * @return How many copies are available
     */
    public Long copiesAvailable() {
        return getCopies() - lendings.stream().filter(l -> !l.isCompleted()).collect(Collectors.toList()).size();
    }

    /**
     * States if there is at least one more copy of thi
     * publication that can be borrowed
     *
     * @return If a copy of publication is available
     */
    public boolean isCopyAvailable() {
        return copiesAvailable() > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publication that = (Publication) o;
        return Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}
