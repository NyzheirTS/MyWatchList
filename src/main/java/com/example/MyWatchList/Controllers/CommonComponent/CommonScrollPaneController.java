package com.example.MyWatchList.Controllers.CommonComponent;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;

public class CommonScrollPaneController {
    @FXML private ScrollPane commonScrollPane;

    public void makePage(Node node){
        commonScrollPane.setContent(node);
    }
}
