package app.orm;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "INVOICES")
public class Invoice {
    @Id
    @GeneratedValue(generator = "invoice_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "invoice_seq",sequenceName = "invoice_seq",initialValue = 1,allocationSize = 1)
    private int id;
    @Column(name = "in_date")
    @Temporal(TemporalType.DATE)
    private Calendar calendar;

    @OneToMany(mappedBy = "invoice", fetch = FetchType.EAGER)
    private List<Invoice_Good> invoice_goods = new ArrayList<Invoice_Good>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "partner_id")
    private Partner partner;

    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public List<Invoice_Good> getInvoice_goods() {
        return invoice_goods;
    }

    public void setInvoice_goods(List<Invoice_Good> invoice_goods) {
        this.invoice_goods = invoice_goods;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Invoice(Calendar calendar) {
        this.calendar = calendar;
    }

    public Invoice() {
    }
}
