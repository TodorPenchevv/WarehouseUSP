package app.business.create;

import app.business.GetSession;
import app.business.exceptions.CustomException;
import app.business.validators.Balance;
import app.business.validators.GoodQuantity;
import app.business.validators.DateValidator;
import app.gui.AlertBox;
import app.orm.*;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.*;

public class InsertInvoice {
    public static void create(LocalDate date, List<Good> goods, int userID, int partnerID, Transactions transactionName) throws Exception{
        Session session = GetSession.getSession();

        //Find duplicate goods and combine their quantity
        List<Good> removeGoods = new ArrayList<>();
        for(Good good1 : goods) {
            if(good1.getQuantity() == 0) {
                removeGoods.add(good1);
                continue;
            }

            for(Good good2 : goods) {
                if(!good1.equals(good2) && good1.getGood().equals(good2.getGood())) {
                    good1.setQuantity(good1.getQuantity() + good2.getQuantity());
                    good2.setQuantity(0);
                }
            }
        }

        //Remove duplicate rows
        goods.removeAll(removeGoods);

        if(transactionName.equals(Transactions.SALE)) {
            //If we are selling...
            //Validate goods quantity is enough
            new GoodQuantity(goods).validate();
        }
        else {
            //If we are buying...
            //Validate we have enough money
            new Balance(goods).validate();
        }

        //Dates are valid
        new DateValidator(date).validate();

        //Goods list is not empty
        if(goods.isEmpty())
            throw new CustomException("Изберете стоки!");

        Calendar invoiceDate = new GregorianCalendar(date.getYear(), date.getMonthValue()-1, date.getDayOfMonth());
        Invoice newInvoice = new Invoice(invoiceDate);

        session.beginTransaction();

        User user = session.get(User.class, userID);
        user.getInvoices().add(newInvoice);
        newInvoice.setUser(user);

        Partner partner = session.get(Partner.class, partnerID);
        partner.getInvoices().add(newInvoice);
        newInvoice.setPartner(partner);

        int transactionId = 1;
        if(transactionName.equals(Transactions.PURCHASE))
              transactionId = 2;

        Transaction transaction = session.get(Transaction.class, transactionId);
        transaction.getInvoices().add(newInvoice);
        newInvoice.setTransaction(transaction);

        session.save(newInvoice);
        session.update(user);
        session.update(partner);
        session.update(transaction);

        session.getTransaction().commit();


        for (Good good : goods){
            InsertInvoiceGood.create(good.getQuantity(), good.getPrice(), newInvoice.getId(), good.getId(), session);
        }

        //Balance alert
        Register register = session.get(Register.class, 1);
        if(register.getBalance() < 5000) {
            AlertBox.display("Наличност", "Наличността в касата е под 5000 лв.!");
        }

        //Close session after all goods are added
        session.close();
    }
}
