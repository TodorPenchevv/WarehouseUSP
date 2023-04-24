package app.business.create;

import app.business.GetSession;
import app.business.validators.GoodValidator;
import app.business.validators.Price;
import app.business.validators.Quantity;
import app.orm.Good;
import org.hibernate.Session;

public class InsertGood {
    public static void create(String good, int quantity, double price, int minQuantity) throws Exception {
        Session session = GetSession.getSession();

        //Data validations...
        new Quantity(quantity).validate();
        new Quantity(minQuantity).validate();
        new Price(price).validate();
        new GoodValidator(good).validate();

        Good newGood = new Good(good, quantity, price, minQuantity);

        session.beginTransaction();
        session.save(newGood);
        session.getTransaction().commit();
        session.close();
    }
}
