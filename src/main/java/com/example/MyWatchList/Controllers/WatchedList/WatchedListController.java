package com.example.MyWatchList.Controllers.WatchedList;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class WatchedListController {
    VBox pnItems;
    Button  addNodeButton;

    public WatchedListController(VBox pnItems, Button addNodeButton){
        this.pnItems = pnItems;
        this.addNodeButton = addNodeButton;
        initializeShowNodes();
        addNewItem();
    }



    private void addNewItem() {
        addNodeButton.setOnMouseClicked(event -> {
            int index = pnItems.getChildren().size();
            WatchedShowsNode newNode = new WatchedShowsNode(String.format("%.2f", 10 * 100 * Math.random()), index);
            newNode.bindWidth(pnItems);
            pnItems.getChildren().add(newNode.getNode());
        });
    }

    private void initializeShowNodes(){
        for ( int i = 0; i<10; ++i){
            WatchedShowsNode node = new WatchedShowsNode(String.format("%.2f", 10 * 100 * Math.random()),i);
            node.bindWidth(pnItems);
            pnItems.getChildren().add(node.getNode());
        }
    }
}
