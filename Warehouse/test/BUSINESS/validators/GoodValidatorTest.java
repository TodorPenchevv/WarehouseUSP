package test.business.validators;

import app.business.GetSession;
import app.business.exceptions.GoodAlreadyExistsException;
import app.business.validators.GoodValidator;
import app.orm.Good;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoodValidatorTest {
    @Test
    void goodNotExists() {
        //This good doesn't exist so everything should be valid
        assertDoesNotThrow(() -> {
            new GoodValidator("Test Good 1").validate();
        });
    }

    @Test
    void goodExists() {
        Session session = GetSession.getSession();

        Good good1 = new Good();
        good1.setGood("Test Good 1");

        session.beginTransaction();
        session.save(good1);
        session.getTransaction().commit();

        //Test Good 1 already exists so this validator should throw error
        assertThrows(GoodAlreadyExistsException.class, () -> {
            new GoodValidator("Test Good 1").validate();
        });

        session.beginTransaction();
        session.delete(good1);
        session.getTransaction().commit();
        session.close();
    }
}