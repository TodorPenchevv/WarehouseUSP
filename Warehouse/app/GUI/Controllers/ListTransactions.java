package app.gui.Controllers;

import app.business.exceptions.CustomException;
import app.business.repository.InvoiceRepository;
import app.business.tools.CustomRow;
import app.business.tools.DateConverter;
import app.gui.AlertBox;
import app.logging.ErrorLogging;
import app.logging.ExceptionToString;
import app.orm.Invoice;
import app.orm.Invoice_Good;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.*;

public class ListTransactions {
    @FXML private DatePicker start;
    @FXML private DatePicker end;

    @FXML private TableView<CustomRow> table;
    @FXML private TableColumn<CustomRow, Calendar> dateColumn;
    @FXML private TableColumn<CustomRow, String> transactionColumn;
    @FXML private TableColumn<CustomRow, String> partnerColumn;
    @FXML private TableColumn<CustomRow, String> userColumn;
    @FXML private TableColumn<CustomRow, String> priceColumn;

    public void submitButtonClicked(){
        try {
            LocalDate startDate = start.getValue();
            LocalDate endDate = end.getValue();

            List<Invoice> invoices = InvoiceRepository.findByPeriod(startDate, endDate);
            loadDataIntoTable(invoices);
        } catch (CustomException e) {
            AlertBox.display("Грешни данни", e.getMessage());
        } catch (Exception e) {
            new ErrorLogging().log(ExceptionToString.convert(e));
        }
    }

    public void loadDataIntoTable(List<Invoice> invoices) {
        ObservableList<CustomRow> list  = FXCollections.observableArrayList();
        String rowTransaction, rowPartnerName, rowUserName, rowDate;
        double rowTotalPrice = 0;

        for (Invoice invoice : invoices){
            rowTransaction = invoice.getTransaction().getTransaction().toString();
            rowPartnerName = invoice.getPartner().getName();
            rowUserName = invoice.getUser().getName();
            rowDate = DateConverter.convert(invoice.getCalendar());

            for (Invoice_Good ig : invoice.getInvoice_goods()) {
                rowTotalPrice += ig.getPrice() * ig.getQuantity();
            }

            CustomRow row = new CustomRow.Builder()
                    .withTransaction(rowTransaction)
                    .withPartnerName(rowPartnerName)
                    .withUserName(rowUserName)
                    .withDate(rowDate)
                    .withTotalPrice(rowTotalPrice).build();
            list.add(row);
            rowTotalPrice=0;
        }

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        transactionColumn.setCellValueFactory(new PropertyValueFactory<>("transaction"));
        partnerColumn.setCellValueFactory(new PropertyValueFactory<>("partnerName"));
        userColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        table.setItems(list);
    }
}
