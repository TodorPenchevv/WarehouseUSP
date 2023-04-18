package app.orm;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PARTNERS")
public class Partner {
    @Id
    @GeneratedValue(generator = "partner_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "partner_seq",sequenceName = "partner_seq",initialValue = 1,allocationSize = 1)
    private int id;
    private String name;
    private String phone;
    private String email;

    @OneToMany(mappedBy = "partner")
    private List<Invoice> invoices = new ArrayList<Invoice>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public Partner(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public Partner() {
    }

    @Override
    public String toString() {
        return name;
    }
}
