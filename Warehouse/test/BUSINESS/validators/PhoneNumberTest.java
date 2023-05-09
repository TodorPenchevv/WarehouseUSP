package test.business.validators;

import app.business.exceptions.InvalidPhoneNumberException;
import app.business.validators.PhoneNumber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberTest {
    @Test
    void correctPhone() {
        assertDoesNotThrow(() -> {
            new PhoneNumber("0899123123").validate();
        });
    }

    @Test
    void tooShortPhone() {
        assertThrows(InvalidPhoneNumberException.class, () -> {
            new PhoneNumber("08991231239").validate();
        });
    }

    @Test
    void tooLongPhone() {
        assertThrows(InvalidPhoneNumberException.class, () -> {
            new PhoneNumber("089912312").validate();
        });
    }

    @Test
    void invalidFormatPhone() {
        assertThrows(InvalidPhoneNumberException.class, () -> {
            new PhoneNumber("0639123123").validate();
        });
    }
}