package app;

import app.business.InitializeData;
import app.gui.SceneManager;
import app.logging.InfoLogging;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Initialize DB
        new InitializeData();

        //Log4j
        new InfoLogging().log("Starting the application...");

        //JavaFX Setup
        SceneManager loadScene = new SceneManager();
        loadScene.load(primaryStage, "Вход в системата", "views/login.fxml");


        new InitializeData();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
