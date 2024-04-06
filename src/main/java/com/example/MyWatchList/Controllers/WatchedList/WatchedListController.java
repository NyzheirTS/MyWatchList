package com.example.MyWatchList.Controllers.WatchedList;

import com.example.MyWatchList.AppConfig.AppCleaner;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class WatchedListController {
    @FXML private Button addNodeButton;
    @FXML private VBox listHome;


    public void initWatchedList() {
        addNewItem();
        initializeShowNodes();
    }


    private void addNewItem() {
        addNodeButton.setOnMouseClicked(event -> {
            int index = listHome.getChildren().size();
            HBox newNode = WatchedListFactory.createListItem(String.format("%.2f", 10 * 100 * Math.random()),index);
            listHome.getChildren().add(newNode);
        });
    }

    private void initializeShowNodes(){
        for ( int i = 0; i<10; ++i){
           HBox node = WatchedListFactory.createListItem(String.format("%.2f", 10 * 100 * Math.random()),i);
           listHome.getChildren().addAll(node);
        }
    }

}
