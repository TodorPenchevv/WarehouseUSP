package app.gui.Controllers;

import app.business.CurrentUser;
import app.business.repository.UserRepository;
import app.gui.SceneManager;
import app.orm.User;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.List;

public class Login {
    public TextField username;
    public PasswordField password;
    public Button button;
    public Label errorMsg;


    public void loginButtonClicked() throws Exception {
        //Find user by username
        List<User> users = UserRepository.findByUsername(username.getText());

        if(users.isEmpty()) {
            errorMsg.setVisible(true);
        } else {
            User foundUser = users.get(0);

            if(password.getText().equals(foundUser.getPassword())) {
                //User object is now keeping the logged-in user data
                CurrentUser.getInstance().login(foundUser.getId(), foundUser.getRole().getId());

                //Enter the app...
                //Load the new scene in the current stage
                SceneManager loadScene = new SceneManager();
                loadScene.loadFromElement(button, "Складова програма", "views/layout.fxml");
            } else {
                errorMsg.setVisible(true);
            }
        }
    }
}
