package app.gui.Controllers;

import app.business.repository.GoodRepository;
import app.logging.ErrorLogging;
import app.logging.ExceptionToString;
import app.orm.Good;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ListGoods implements Initializable {
    @FXML private TableView<Good> table;
    @FXML private TableColumn<Good, String> goodColumn;
    @FXML private TableColumn<Good, Double> priceColumn;
    @FXML private TableColumn<Good, Integer> quantityColumn;
    @FXML private TableColumn<Good, Integer> minQuantityColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ObservableList<Good> list = FXCollections.observableArrayList();
            list.addAll(GoodRepository.findAll());
            goodColumn.setCellValueFactory(new PropertyValueFactory<>("good"));
            priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
            quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            minQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("minQuantity"));
            table.setItems(list);
        } catch (Exception e) {
            new ErrorLogging().log(ExceptionToString.convert(e));
        }
    }
}
