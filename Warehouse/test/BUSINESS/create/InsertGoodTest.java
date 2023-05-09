package test.business.create;

import app.business.GetSession;
import app.business.create.InsertGood;
import app.business.repository.GoodRepository;
import app.orm.Good;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InsertGoodTest {
    @Test
    void successfullyCreatedGood() {
        //Assert creating good with valid data
        //does not throw any exceptions
        assertDoesNotThrow(() -> {
            InsertGood.create("Test good 1", 20, 199.99, 10);
        });

        //Assert that the good was saved successfully in the database
        List<Good> goods = GoodRepository.findByGood("Test good 1");
        assertEquals("Test good 1", goods.get(0).getGood());

        //Remove the test good
        deleteTestGood();
    }

    void deleteTestGood() {
        Session session = GetSession.getSession();

        List<Good> goods = GoodRepository.findByGood("Test good 1");

        session.beginTransaction();
        session.delete(goods.get(0));
        session.getTransaction().commit();
        session.close();
    }
}