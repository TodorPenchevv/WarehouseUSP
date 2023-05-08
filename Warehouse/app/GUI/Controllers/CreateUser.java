package app.gui.Controllers;

import app.logging.ErrorLogging;
import app.business.create.InsertUser;
import app.business.exceptions.CustomException;
import app.gui.AlertBox;
import app.logging.ExceptionToString;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CreateUser {
    @FXML private TextField fullName;
    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private PasswordField passwordConfirm;
    @FXML private CheckBox adminCheckbox;
    @FXML private Button submitForm;

    public void submitForm() {
        try {
            String fullNameText = fullName.getText();
            String usernameText = username.getText();
            String passwordText = password.getText();
            String passwordConfirmText = passwordConfirm.getText();
            int admin = 1;

            if(adminCheckbox.isSelected()) {
                admin = 2;
            }

            InsertUser.create(fullNameText, usernameText, passwordText, passwordConfirmText, admin);
            fullName.setText("");
            username.setText("");
            password.setText("");
            passwordConfirm.setText("");
            adminCheckbox.setSelected(false);

            AlertBox.display("Съобщение", "Успешно Създаване!");
        } catch (CustomException e) {
            AlertBox.display("Грешни данни", e.getMessage());
        } catch (Exception e) {
            new ErrorLogging().log(ExceptionToString.convert(e));
        }
    }
}
