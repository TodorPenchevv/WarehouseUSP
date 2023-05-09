package test.business.validators;

import app.business.exceptions.InvalidDateException;
import app.business.validators.DateValidator;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class DateValidatorTest {

    @Test
    void validDate() {
        LocalDate date = LocalDate.of(2001, Month.APRIL, 1);

        assertDoesNotThrow(() -> {
            new DateValidator(date).validate();
        });

    }

    @Test
    void invalidDate() {
        LocalDate date = null;

        assertThrows(InvalidDateException.class, () -> {
            new DateValidator(date).validate();
        });
    }
}