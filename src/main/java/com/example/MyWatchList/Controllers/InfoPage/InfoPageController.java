package com.example.MyWatchList.Controllers.InfoPage;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;

public class InfoPageController {

    @FXML private ScrollPane middleContainer;
    @FXML private BorderPane InfopageHome;

    public void updatepage(int nodeID, String media_type){
        middleContainer.setContent(new Label(String.valueOf(nodeID) + "  " +  media_type));
    }
}
