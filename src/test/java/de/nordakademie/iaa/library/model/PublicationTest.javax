package de.nordakademie.iaa.library.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Felix Welter
 */
public class PublicationTest {
    @Test
    void testCopiesAvailable() {
        Publication publication = new Publication();
        publication.setKey("BG-112");
        publication.setCopies(new Long(10));
        publication.setLendings(new ArrayList<Lending>());

        Lending l1 = new Lending();
        publication.addLending(l1);

        Lending l2 = new Lending();
        publication.addLending(l2);

        assertThat(publication.copiesAvailable()).isEqualTo(8);

        l2.setReturned(true);

        assertThat(publication.copiesAvailable()).isEqualTo(9);
    }


    @Test
    void testIsCopyAvailable() {
        Publication publication = new Publication();
        publication.setKey("BG-113");
        publication.setCopies(new Long(3));
        publication.setLendings(new ArrayList<Lending>());

        Lending l1 = new Lending();
        publication.addLending(l1);

        Lending l2 = new Lending();
        publication.addLending(l2);

        assertThat(publication.isCopyAvailable()).isTrue();

        Lending l3 = new Lending();
        publication.addLending(l3);

        assertThat(publication.isCopyAvailable()).isFalse();

        l2.setReturned(true);

        assertThat(publication.isCopyAvailable()).isTrue();
    }

    @Test
    void testAddLending() {
        Lending lending = new Lending();
        Publication publication = new Publication();
        publication.setLendings(new ArrayList<Lending>());

        publication.addLending(lending);

        assertThat(lending.getPublication().equals(lending));
    }
}
