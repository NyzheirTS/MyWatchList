package com.example.MyWatchList.Controllers.InfoPage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;
import java.util.Objects;

public class InfoPageController {


    private int mediaID;
    private String media_type;
    public void initInfoPage(int MediaID, String MediaType){
        this.media_type = MediaType;
        this.mediaID = MediaID;
    }

}
