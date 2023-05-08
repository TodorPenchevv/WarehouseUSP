package app.gui.Controllers;

import app.business.CurrentUser;
import app.logging.ErrorLogging;
import app.business.exceptions.NotAdminException;
import app.business.tools.Branch;
import app.gui.AlertBox;
import app.gui.SceneManager;
import app.gui.ViewManager;
import app.logging.ExceptionToString;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Layout implements Initializable {
    @FXML private TreeView<String> treeView;
    @FXML private AnchorPane mainPane;
    @FXML private Button logoutButton;

    private ViewManager viewLoader = new ViewManager();

    @Override
    public void initialize(URL location, ResourceBundle resource) {
        this.treeViewSetup();
        viewLoader.load(mainPane, "views/welcome.fxml");
    }

    private void treeViewSetup() {
        TreeItem<String> rootItem = new TreeItem<>();
        rootItem.setExpanded(true);

        TreeItem<String> users = Branch.create("Работа с Потребители", rootItem);
        Branch.create("Създаване на Потребител", users);
        Branch.create("Справка за Потребител", users);

        TreeItem<String> partners = Branch.create("Работа с Партньори", rootItem);
        Branch.create("Създаване на Партньор", partners);

        TreeItem<String> goods = Branch.create("Работа със Стоки", rootItem);
        Branch.create("Създаване на Стока", goods);
        Branch.create("Списък с Наличности", goods);
        Branch.create("Справка за Стока", goods);

        TreeItem<String> invoices = Branch.create("Работа с Фактури", rootItem);
        Branch.create("Създаване на Фактура", invoices);
        Branch.create("Списък с Изписвания/Доставки", invoices);

        TreeItem<String> register = Branch.create("Работа с Каса", rootItem);
        Branch.create("Създаване на Каса", register);
        Branch.create("Наличност в Каса", register);
        Branch.create("Списък с Транзакции", register);
        Branch.create("Приходи - Разходи - Печалба", register);

        treeView.setShowRoot(false);
        treeView.setRoot(rootItem);
    }

    public void selectItems() {
        try {
            TreeItem<String> item = treeView.getSelectionModel().getSelectedItem();

            if(item != null) {
                viewLoader.chooseView(mainPane, item.getValue());
            }
        } catch (NotAdminException e) {
            AlertBox.display("Достъп", "Нямаш права за този раздел!");
        } catch (Exception e) {
            new ErrorLogging().log(ExceptionToString.convert(e));
        }
    }

    public void logout() {
        //Set the user as logged out
        CurrentUser.getInstance().logout();
        //Set the scene to login
        SceneManager loadScene = new SceneManager();
        loadScene.loadFromElement(logoutButton, "Вход в системата", "views/login.fxml");
    }
}
