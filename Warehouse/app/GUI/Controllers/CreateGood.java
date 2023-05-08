package app.gui.Controllers;

import app.logging.ErrorLogging;
import app.business.create.InsertGood;
import app.business.exceptions.CustomException;
import app.gui.AlertBox;
import app.logging.ExceptionToString;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CreateGood {
    @FXML private TextField good;
    @FXML private TextField price;
    @FXML private TextField quantity;
    @FXML private TextField minQuantity;

    public void createButtonClicked() {
        try {
            String newGood = good.getText();
            int newQuantity = Integer.parseInt(quantity.getText());
            int newMinQuantity = Integer.parseInt(minQuantity.getText());
            double newPrice = Double.parseDouble(price.getText());

            InsertGood.create(newGood, newQuantity, newPrice, newMinQuantity);
            good.setText("");
            quantity.setText("");
            minQuantity.setText("");
            price.setText("");
            AlertBox.display("Съобщение", "Успешно създаване!");
        } catch (CustomException e) {
            AlertBox.display("Грешни данни", e.getMessage());
        } catch (NumberFormatException e) {
            AlertBox.display("Грешни данни", "Въведете числа в полетата за цена и стойност!");
        } catch (Exception e) {
            new ErrorLogging().log(ExceptionToString.convert(e));
        }
    }
}
