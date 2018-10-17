package de.nordakademie.iaa.library.model;

import de.nordakademie.iaa.library.dao.KeywordAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.mockito.cglib.core.Local;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @Test
    void testErrorOnTooManyProlongations() {
        Lending lending = new Lending();
        lending.setStartDate(date(1));
        lending.setEndDate(date(2));
        lending.setTimesProlonged(new Long(1));

        lending.prolongUntil(date(3));

        assertThatThrownBy(() -> lending.prolongUntil(date(3))).isInstanceOf(ProlongationNotPossible.class);
    }

    @Test
    void testReminderDue() {
        Lending lending = new Lending();
        lending.setReminders(new ArrayList<Reminder>());
        Reminder reminder = new Reminder();
        reminder.setDate(LocalDate.of(2000, 1, 1));
        lending.addReminder(reminder);

        assertThat(lending.isReminderDue()).isTrue();

        Reminder reminder2 = new Reminder();
        reminder2.setDate(LocalDate.now());
        lending.addReminder(reminder2);

        assertThat(lending.isReminderDue()).isFalse();
    }

    @Test
    void testReminderDueAfterReturn() {
        Lending lending = new Lending();
        lending.setReminders(new ArrayList<Reminder>());
        Reminder reminder = new Reminder();
        reminder.setDate(LocalDate.of(2000, 1, 1));
        lending.addReminder(reminder);

        assertThat(lending.isReminderDue()).isTrue();

        lending.setReturned(true);

        assertThat(lending.isReminderDue()).isFalse();
    }

    @Test
    void testReminderDueAfterLoss() {
        Lending lending = new Lending();
        lending.setReminders(new ArrayList<Reminder>());
        Reminder reminder = new Reminder();
        reminder.setDate(LocalDate.of(2000, 1, 1));
        lending.addReminder(reminder);

        assertThat(lending.isReminderDue()).isTrue();

        lending.setLost(true);

        assertThat(lending.isReminderDue()).isFalse();
    }
}
