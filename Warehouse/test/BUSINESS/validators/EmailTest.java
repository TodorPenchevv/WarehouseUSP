package test.business.validators;

import app.business.exceptions.InvalidEmailException;
import app.business.validators.Email;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {
    @Test
    void basicPattern() {
        assertThrows(InvalidEmailException.class, () -> {
            new Email("maildomail.com").validate();
        });
    }

    @Test
    void strictPattern() {
        assertThrows(InvalidEmailException.class, () -> {
            new Email("username@.com").validate();
        });
    }

    @Test
    void RFC5322Pattern() {
        assertThrows(InvalidEmailException.class, () -> {
            new Email("user|name@domain.com").validate();
        });
    }

    @Test
    void topLevelDomainPattern() {
        assertThrows(InvalidEmailException.class, () -> {
            new Email("username@domain.c").validate();
        });
    }

    @Test
    void dotsPattern() {
        assertThrows(InvalidEmailException.class, () -> {
            new Email("user..name@domain.com").validate();
        });
    }
}