package test.business.validators;

import app.business.GetSession;
import app.business.exceptions.UsernameTakenException;
import app.business.validators.Username;
import app.orm.User;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsernameTest {
    @Test
    void usernameDoesNotExist() {
        assertDoesNotThrow(() -> {
             new Username("testusername1").validate();
        });
    }

    @Test
    void usernameExists() {
        Session session = GetSession.getSession();

        User testUser = new User();
        testUser.setUsername("testusername1");

        session.beginTransaction();
        session.save(testUser);
        session.getTransaction().commit();

        //Test User 1 already exists so this validator should throw error
        assertThrows(UsernameTakenException.class, () -> {
            new Username("testusername1").validate();
        });

        session.beginTransaction();
        session.delete(testUser);
        session.getTransaction().commit();
        session.close();
    }
}