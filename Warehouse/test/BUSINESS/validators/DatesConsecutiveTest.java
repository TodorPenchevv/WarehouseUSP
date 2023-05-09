package test.business.validators;

import app.business.exceptions.DatesNotConsecutive;
import app.business.validators.DatesConsecutive;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class DatesConsecutiveTest {
    @Test
    void consecutiveDates() {
        LocalDate date1 = LocalDate.of(2001, Month.APRIL, 1);
        LocalDate date2 = LocalDate.of(2004, Month.MARCH, 1);

        assertDoesNotThrow(() -> {
            new DatesConsecutive(date1, date2).validate();
        });

    }

    @Test
    void nonConsecutiveDates() {
        LocalDate date1 = LocalDate.of(2001, Month.APRIL, 1);
        LocalDate date2 = LocalDate.of(2004, Month.MARCH, 1);

        assertThrows(DatesNotConsecutive.class, () -> {
            new DatesConsecutive(date2, date1).validate();
        });
    }
}