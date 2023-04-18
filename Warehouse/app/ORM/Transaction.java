package app.orm;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TRANSACTIONS")
public class Transaction {
    @Id
    private int id;
    @Enumerated(EnumType.STRING)
    private Transactions transaction;

    @OneToMany(mappedBy = "transaction")
    private List<Invoice> invoices = new ArrayList<Invoice>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Transactions getTransaction() {
        return transaction;
    }

    public void setTransaction(Transactions transaction) {
        this.transaction = transaction;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public Transaction(int id, Transactions transaction) {
        this.id = id;
        this.transaction = transaction;
    }

    public Transaction() {
    }
}
