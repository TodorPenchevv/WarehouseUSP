package test.business.create;

import app.business.GetSession;
import app.business.create.InsertPartner;
import app.business.repository.PartnerRepository;
import app.orm.Partner;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InsertPartnerTest {
    @Test
    void successfullyAddedPartner() {
        //Assert creating user with valid data
        //does not throw any exceptions
        assertDoesNotThrow(() -> {
            InsertPartner.create("Test Partner 1", "0888123123", "mail@testfirma.bg");
        });

        //Assert that the user was saved successfully in the database
        List<Partner> partners = PartnerRepository.findByName("Test Partner 1");
        assertEquals("Test Partner 1", partners.get(0).getName());

        //Remove the test user
        deleteTestPartners();
    }

    void deleteTestPartners() {
        Session session = GetSession.getSession();

        List<Partner> partners = PartnerRepository.findByName("Test Partner 1");

        session.beginTransaction();
        session.delete(partners.get(0));
        session.getTransaction().commit();
        session.close();
    }
}