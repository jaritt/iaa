package de.nordakademie.iaa.library.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class KeywordTest {

    @Test
    void testConstructor() {
        Keyword keyword = new Keyword("testme");
        assertThat("testme".equals(keyword.getWord())).isTrue();
    }

    @Test
    public void testKeywordsEqual() {
        Keyword keyword1 = new Keyword();
        keyword1.setWord("hello");

        Keyword keyword2 = new Keyword();
        keyword2.setWord("hello");

        assertThat(keyword1.equals(keyword2)).isTrue();
    }

    @Test
    public void testKeywordsDifferent() {
        Keyword keyword1 = new Keyword();
        keyword1.setWord("type1");

        Keyword keyword2 = new Keyword();
        keyword2.setWord("type2");

        assertThat(keyword1.equals(keyword2)).isFalse();
    }
}
