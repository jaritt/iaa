package de.nordakademie.iaa.library.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PublicationTypeTest {
    @Test
    public void testPublicationTypesEqual() {
        PublicationType type1 = new PublicationType();
        type1.setTitle("Bachelorarbeit");

        PublicationType type2 = new PublicationType();
        type2.setTitle("Bachelorarbeit");

        assertThat(type1.equals(type2)).isTrue();
    }

    @Test
    public void testPublicationTypesDifferent() {
        PublicationType type1 = new PublicationType();
        type1.setTitle("Masterarbeit");

        PublicationType type2 = new PublicationType();
        type2.setTitle("Hausarbeit");

        assertThat(type1.equals(type2)).isFalse();
    }
}
