package de.nordakademie.iaa.library.model;

import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Felix Welter
 */
public class LendingTest {
    @Test
    void testIsCompleted() {
        Lending lending = new Lending();
        assertThat(lending.isCompleted()).isFalse();
        lending.setReturned(true);
        assertThat(lending.isCompleted()).isTrue();
        lending.setLost(true);
        assertThat(lending.isCompleted()).isTrue();

        Lending lending1 = new Lending();
        lending1.setLost(true);
        assertThat(lending1.isCompleted()).isTrue();
    }

    LocalDate date(int month) {
        return LocalDate.of(2018, month, 01);
    }

    @Test
    void testProlongation() {
        Lending lending = new Lending();
        lending.setStartDate(date(1));
        lending.setEndDate(date(2));

        assertThat(lending.canBeProlonged()).isTrue();

        lending.prolongUntil(date(3));

        assertThat(lending.canBeProlonged()).isTrue();

        lending.prolongUntil(date(4));

        assertThat(lending.canBeProlonged()).isFalse();

        assertThat(lending.getEndDate().equals(date(4))).isTrue();
    }
}
