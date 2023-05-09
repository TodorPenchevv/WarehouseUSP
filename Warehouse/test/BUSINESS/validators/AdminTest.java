package test.business.validators;

import app.business.CurrentUser;
import app.business.exceptions.NotAdminException;
import app.business.validators.Admin;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminTest {
    @Test
    void userIsAdmin() {
        CurrentUser.getInstance().login(1, 2);
        assertDoesNotThrow(() -> {
            new Admin().validate();
        });
    }

    @Test
    void userIsNotAdmin() {
        CurrentUser.getInstance().login(1, 1);
        assertThrows(NotAdminException.class, () -> {
            new Admin().validate();
        });
    }
}