package app.business.create;

import app.gui.AlertBox;
import app.orm.*;
import org.hibernate.Session;

public class InsertInvoiceGood {
    public static void create(int quantity, double price, int invoiceID, int goodID, Session session) {
        Invoice_Good newInvoiceGood = new Invoice_Good(quantity, price);

        session.beginTransaction();

        Invoice invoice = session.get(Invoice.class, invoiceID);
        invoice.getInvoice_goods().add(newInvoiceGood);
        newInvoiceGood.setInvoice(invoice);

        Good good = session.get(Good.class, goodID);
        good.getInvoice_goods().add(newInvoiceGood);
        newInvoiceGood.setGood(good);

        Register register = session.get(Register.class, 1);

        double newBalance;
        int newQuantity;

        if (invoice.getTransaction().getTransaction().equals(Transactions.PURCHASE)){
            newBalance = register.getBalance() - quantity*price;
            newQuantity = good.getQuantity() + quantity;
        }
        else{
            newBalance = register.getBalance() + quantity*price;
            newQuantity = good.getQuantity() - quantity;

            if(newQuantity < good.getMinQuantity()) {
                AlertBox.display("Наличност", "Наличността от " + good.getGood() + " е под " + good.getMinQuantity() + "!");
            }
        }

        register.setBalance(newBalance);
        good.setQuantity(newQuantity);

        session.save(newInvoiceGood);
        session.update(invoice);
        session.update(good);
        session.update(register);
        session.getTransaction().commit();
    }
}
