package de.nordakademie.iaa.library.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * This class represents a keyword. Keywords are assignable
 * to publications. Helpful during a search process or
 * categorisation.
 *
 * @author Felix Welter
 * @see Publication
 */
@Entity
public class Keyword {

    public Keyword() {
    }

    public Keyword(String word) {
        this.word = word;
    }

    /**
     * The Identifier
     */
    private Long id;

    /**
     * The actual word
     */
    private String word;

    /**
     * List of related publications
     */
    private List<Publication> publications;

    @Basic
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Keyword keyword = (Keyword) o;
        return Objects.equals(word, keyword.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word);
    }

    @ManyToMany(cascade = {
            CascadeType.MERGE
    }, fetch = FetchType.EAGER)
    @JoinTable(
            name = "publication_keyword",
            joinColumns = @JoinColumn(name = "keyword_id"),
            inverseJoinColumns = @JoinColumn(name = "publication_id")
    )
    public List<Publication> getPublications() {
        return publications;
    }

    public void setPublications(List<Publication> publications) {
        this.publications = publications;
    }
}
