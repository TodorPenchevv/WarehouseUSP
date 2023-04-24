package app.business.create;

import app.business.GetSession;
import app.orm.Transaction;
import app.orm.Transactions;
import org.hibernate.Session;

public class InsertTransaction {
    public static void create(int id, Transactions transaction) {
        Session session = GetSession.getSession();

        Transaction newTransaction = new Transaction(id, transaction);

        session.beginTransaction();
        session.save(newTransaction);
        session.getTransaction().commit();
        session.close();
    }
}
