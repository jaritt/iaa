package de.nordakademie.iaa.library.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Keyword {

    /**
     * The Identifier
     */
    private Long id;

    /**
     * The actual word
     */
    private String word;

    @NaturalId
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
}
