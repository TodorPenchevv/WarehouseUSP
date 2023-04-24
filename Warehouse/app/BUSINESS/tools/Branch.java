package app.business.tools;

import javafx.scene.control.TreeItem;

public class Branch {

    //Create branches
    public static TreeItem<String> create(String title, TreeItem<String> parent) {
        TreeItem<String> item = new TreeItem<>(title);
        item.setExpanded(true);
        parent.getChildren().add(item);
        return item;
    }
}
