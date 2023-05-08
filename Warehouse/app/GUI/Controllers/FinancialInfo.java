package app.gui.Controllers;

import app.business.exceptions.CustomException;
import app.business.repository.InvoiceRepository;
import app.gui.AlertBox;
import app.logging.ErrorLogging;
import app.logging.ExceptionToString;
import app.orm.Invoice;
import app.orm.Invoice_Good;
import app.orm.Transactions;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.time.LocalDate;
import java.util.List;

public class FinancialInfo {
    @FXML private DatePicker start;
    @FXML private DatePicker end;

    @FXML private Label proceedLabel;
    @FXML private Label expenseLabel;
    @FXML private Label profitLabel;


    public void submitButtonClicked(){
        try {
            LocalDate startDate = start.getValue();
            LocalDate endDate = end.getValue();

            List<Invoice> invoices = InvoiceRepository.findByPeriod(startDate, endDate);
            loadFinancialInfo(invoices);
        } catch(CustomException e) {
            AlertBox.display("Грешни данни", e.getMessage());
        } catch(Exception e) {
            new ErrorLogging().log(ExceptionToString.convert(e));
        }
    }

    public void loadFinancialInfo(List<Invoice> invoices) {
        double undefined = 0, expenses = 0, proceeds = 0, profit = 0;

        for (Invoice invoice : invoices) {
            for (Invoice_Good invoice_good : invoice.getInvoice_goods()) {
                undefined += invoice_good.getQuantity() * invoice_good.getPrice();
            }
            if (invoice.getTransaction().getTransaction().equals(Transactions.PURCHASE))
                expenses += undefined;
            else
                proceeds += undefined;
            undefined = 0;
        }

        profit = proceeds - expenses;

        proceedLabel.setText("Приходи: " + String.valueOf(proceeds)+"лв.");
        expenseLabel.setText("Разходи: " + String.valueOf(expenses)+"лв.");
        if(profit < 0)
            profitLabel.setText("Загуба: " + String.valueOf(-profit)+"лв.");
        else
            profitLabel.setText("Печалба: " + String.valueOf(profit)+"лв.");
    }
}
