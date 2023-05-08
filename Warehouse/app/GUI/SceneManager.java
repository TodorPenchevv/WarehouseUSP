package app.gui;

import app.logging.ErrorLogging;
import app.logging.ExceptionToString;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {
    public void load(Stage stage, String title, String scenePath) {
        try {
            Parent mainLayout = FXMLLoader.load(getClass().getResource(scenePath));
            stage.setTitle(title);
            stage.setScene(new Scene(mainLayout, 1330, 800));
            stage.show();
        } catch (Exception e) {
            loadFailed(e);
        }
    }

    //Getting an element (Node), finding its parent
    //and then getting the current scene
    public void loadFromElement(Node node, String title, String scenePath) {
        try {
            Parent mainLayout = FXMLLoader.load(getClass().getResource(scenePath));
            //Get current stage
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(mainLayout, 1330, 800));
            stage.show();
        } catch (Exception e) {
            loadFailed(e);
        }
    }

    public void loadFailed(Exception e) {
        new ErrorLogging().log(ExceptionToString.convert(e));
    }
}
