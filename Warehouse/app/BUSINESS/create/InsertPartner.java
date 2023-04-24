package app.business.create;

import app.business.GetSession;
import app.business.exceptions.CustomException;
import app.business.validators.Email;
import app.business.validators.PartnerName;
import app.business.validators.PhoneNumber;
import app.orm.Partner;
import org.hibernate.Session;

public class InsertPartner {
    public static void create(String name, String phone, String email) throws CustomException {
        Session session = GetSession.getSession();

        //Data validation...
        new PartnerName(name).validate();
        new Email(email).validate();
        new PhoneNumber(phone).validate();

        Partner newPartner = new Partner(name, phone, email);

        session.beginTransaction();
        session.save(newPartner);
        session.getTransaction().commit();
        session.close();
    }
}
