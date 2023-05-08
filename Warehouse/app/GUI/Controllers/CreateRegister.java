package app.gui.Controllers;

import app.business.create.InsertRegister;
import app.business.exceptions.CustomException;
import app.gui.AlertBox;
import app.logging.ErrorLogging;
import app.logging.ExceptionToString;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

//only 1 register
public class CreateRegister {
    @FXML private TextField balance;

    public void createButtonClicked(){
        try {
            double newBalance = Double.parseDouble(balance.getText());

            InsertRegister.create(1, newBalance);
            AlertBox.display("Съобщение", "Успешно създаване!");
        } catch (CustomException e) {
            AlertBox.display("Грешка", e.getMessage());
        } catch (Exception e) {
            new ErrorLogging().log(ExceptionToString.convert(e));
        }
    }
}
