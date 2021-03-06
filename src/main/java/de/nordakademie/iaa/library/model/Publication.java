
package de.nordakademie.iaa.library.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents a publication which is owned by the library.
 *
 * @author Felix Welter
 */
@Entity
public class Publication {

    public Publication() {

    }

    public Publication(String key, String title) {
        this.key = key;
        this.title = title;
        this.setKeywords(new ArrayList<>());
        this.setLendings(new ArrayList<>());
    }

    public Publication(String key, String title, PublicationType type, Long copies) {
        this.key = key;
        this.title = title;
        this.type = type;
        this.copies = copies;
        this.setKeywords(new ArrayList<>());
        this.setLendings(new ArrayList<>());
    }

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
    private Long releaseYear;
    private Long releaseMonth;
    private Long releaseDay;

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
    public Long getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Long releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Basic
    public Long getReleaseMonth() {
        return releaseMonth;
    }

    public void setReleaseMonth(Long releaseMonth) {
        this.releaseMonth = releaseMonth;
    }

    @Basic
    public Long getReleaseDay() {
        return releaseDay;
    }

    public void setReleaseDay(Long releaseDay) {
        this.releaseDay = releaseDay;
    }

    /**
     * Return full release date as string
     *
     * @return release date as string
     */
    @Transient
    public String getReleaseDate() {
        if (releaseMonth == null) {
            return releaseYear.toString();
        }
        if (releaseDay == null) {
            if (releaseMonth < 10) {
                return releaseYear + "-0" + releaseMonth;
            } else {
                return releaseYear + "-" + releaseMonth;
            }
        }
        if (releaseMonth < 10) {
            if (releaseDay < 10) {
                return String.valueOf(releaseYear) + "-0" + releaseMonth + "-0" + releaseDay;
            }
            return String.valueOf(releaseYear) + "-0" + releaseMonth + "-" + releaseDay;
        } else if (releaseDay < 10){
            return String.valueOf(releaseYear) + "-" + releaseMonth + "-0" + releaseDay;
        }
        return String.valueOf(releaseYear) + "-" + releaseMonth + "-" + releaseDay;
    }

    @Transient
    public void setReleaseDate(LocalDate date) {
        this.releaseDay = (long) date.getDayOfMonth();
        this.releaseMonth = (long) date.getMonthValue();
        this.releaseYear = (long) date.getYear();
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

    @ManyToMany(cascade = {
            CascadeType.MERGE
    }, fetch = FetchType.EAGER)
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

    @OneToMany()
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Lending> getLendings() {
        return lendings;
    }

    public void setLendings(List<Lending> lendings) {
        this.lendings = lendings;
    }

    @Transient
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
    @Transient
    public Long copiesAvailable() {
        return getCopies() - lendings.stream().filter(l -> !l.isCompleted()).collect(Collectors.toList()).size();
    }

    /**
     * States if there is at least one more copy of thi
     * publication that can be borrowed
     *
     * @return If a copy of publication is available
     */
    @Transient
    public boolean isCopyAvailable() {
        return copiesAvailable() > 0;
    }

    /**
     * Decrease the copy count of
     * this publication by one
     */
    public void decreaseCopyCount() {
        setCopies(getCopies() - 1);
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

    @Transient
    public String getKeywordsAsString() {
        return this.getKeywords().stream().map(Keyword::getWord).collect(Collectors.joining(", "));
    }

    @Transient
    public String getKeywordsAsStringWithoutCommas() {
        return this.getKeywords().stream().map(Keyword::getWord).collect(Collectors.joining(" "));
    }
}
