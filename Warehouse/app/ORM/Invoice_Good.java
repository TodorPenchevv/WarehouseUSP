package app.orm;

import javax.persistence.*;

@Entity
@Table(name = "INVOICE_GOOD")
public class Invoice_Good {
    @Id
    @GeneratedValue(generator = "invoice_good_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "invoice_good_seq",sequenceName = "invoice_good_seq",initialValue = 1,allocationSize = 1)
    private int id;
    @Column(name = "good_quantity")
    private int quantity;
    @Column(name = "good_price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "good_id")
    private Good good;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }

    public Invoice_Good(int quantity, double price) {
        this.quantity = quantity;
        this.price = price;
    }

    public Invoice_Good() {
    }


}
