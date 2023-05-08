package app.gui;

import app.logging.ErrorLogging;
import app.business.validators.Admin;
import app.logging.ExceptionToString;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

//A class that manages menu loading
//base on the user choice

public class ViewManager {
    //Method loading fxml into AnchorPanes
    public void load(AnchorPane pane, String path) {
        try {
            Parent newFile = FXMLLoader.load(getClass().getResource(path));
            pane.getChildren().setAll(newFile);
        } catch(Exception e) {
            new ErrorLogging().log(ExceptionToString.convert(e));
        }
    }

    //Based on the row clicked from the treeView
    //we are loading the corresponding view file (fxml)
    public void chooseView(AnchorPane pane, String option) throws Exception {
        switch(option) {
            case "Създаване на Потребител":
                new Admin().validate();
                this.load(pane, "views/createUser.fxml");
                break;
            case "Справка за Потребител":
                this.load(pane, "views/userQuery.fxml");
                break;
            case "Създаване на Партньор":
                this.load(pane, "views/createPartner.fxml");
                break;
            case "Създаване на Стока":
                this.load(pane, "views/createGood.fxml");
                break;
            case "Списък с Наличности":
                this.load(pane, "views/listGoods.fxml");
                break;
            case "Справка за Стока":
                this.load(pane, "views/goodQuery.fxml");
                break;
            case "Създаване на Фактура":
                this.load(pane, "views/createInvoice.fxml");
                break;
            case "Списък с Изписвания/Доставки":
                this.load(pane, "views/listDeals.fxml");
                break;
            case "Създаване на Каса":
                new Admin().validate();
                this.load(pane, "views/createRegister.fxml");
                break;
            case "Наличност в Каса":
                this.load(pane, "views/currentBalance.fxml");
                break;
            case "Списък с Транзакции":
                this.load(pane, "views/listTransactions.fxml");
                break;
            case "Приходи - Разходи - Печалба":
                this.load(pane, "views/financialInfo.fxml");
                break;
        }
    }
}
