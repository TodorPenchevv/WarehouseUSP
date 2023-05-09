package test.business.validators;

import app.business.GetSession;
import app.business.exceptions.PartnerAlreadyExistsException;
import app.business.validators.PartnerName;
import app.orm.Partner;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PartnerNameTest {
    @Test
    void partnerNotExists() {
        //This good doesn't exist so everything should be valid
        assertDoesNotThrow(() -> {
            new PartnerName("Test Partner 1").validate();
        });
    }

    @Test
    void partnerExists() {
        Session session = GetSession.getSession();

        Partner partner = new Partner();
        partner.setName("Test Partner 1");

        session.beginTransaction();
        session.save(partner);
        session.getTransaction().commit();

        //Test Good 1 already exists so this validator should throw error
        assertThrows(PartnerAlreadyExistsException.class, () -> {
            new PartnerName("Test Partner 1").validate();
        });

        session.beginTransaction();
        session.delete(partner);
        session.getTransaction().commit();
        session.close();
    }
}