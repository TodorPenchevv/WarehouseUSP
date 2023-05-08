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
import app.orm.Transactions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ListDeals implements Initializable {
    @FXML private DatePicker start;
    @FXML private DatePicker end;
    @FXML private RadioButton salesRadio;

    @FXML private TableView<CustomRow> table;
    @FXML private TableColumn<CustomRow, String> dateColumn;
    @FXML private TableColumn<CustomRow, String> userColumn;
    @FXML private TableColumn<CustomRow, String> partnerColumn;
    @FXML private TableColumn<CustomRow, Double> priceColumn;
    @FXML private TableColumn<CustomRow, String> detailColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        salesRadio.setSelected(true);
    }
    @FXML
    void submitButtonClicked() {
        try {
            LocalDate startDate = start.getValue();
            LocalDate endDate = end.getValue();

            Transactions transactionName;
            if (salesRadio.isSelected())
                transactionName = Transactions.SALE;
            else
                transactionName = Transactions.PURCHASE;

            //Get all invoice for the time period
            List<Invoice> invoices = InvoiceRepository.findByPeriod(startDate, endDate);

            //Check if there are any invoices
            if(invoices == null || invoices.isEmpty()) {
                //Tell the user if no invoices are found
                if(transactionName == Transactions.SALE)
                    AlertBox.display("Съобщение", "Няма изписвания за дадения период!");
                else
                    AlertBox.display("Съобщение", "Няма доставки за дадения период!");

                //Remove all items
                table.getItems().clear();
            }
            else {
                loadDataIntoTable(invoices, transactionName);
            }
        } catch(CustomException e) {
            AlertBox.display("Грешни данни", e.getMessage());
        } catch(Exception e) {
            new ErrorLogging().log(ExceptionToString.convert(e));
        }
    }

    public void loadDataIntoTable(List<Invoice> invoices, Transactions transactionName) {
        ObservableList<CustomRow> list = FXCollections.observableArrayList();
        String rowDate, rowUserName, rowPartnerName, rowDetails;
        double rowTotalPrice = 0;

        for (Invoice invoice : invoices) {
            if (invoice.getTransaction().getTransaction().equals(transactionName)) {
                rowDate = DateConverter.convert(invoice.getCalendar());
                rowUserName = invoice.getUser().getName();
                rowPartnerName = invoice.getPartner().getName();
                rowDetails = CustomRow.processDetails(invoice.getInvoice_goods());

                for (Invoice_Good ig : invoice.getInvoice_goods()) {
                    rowTotalPrice += ig.getPrice() * ig.getQuantity();
                }

                CustomRow row = new CustomRow.Builder()
                        .withDate(rowDate)
                        .withUserName(rowUserName)
                        .withPartnerName(rowPartnerName)
                        .withDetails(rowDetails)
                        .withTotalPrice(rowTotalPrice).build();
                list.add(row);
                rowTotalPrice = 0;
            }
        }

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        userColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        partnerColumn.setCellValueFactory(new PropertyValueFactory<>("partnerName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        detailColumn.setCellValueFactory(new PropertyValueFactory<>("details"));

        table.setItems(list);
    }
}

