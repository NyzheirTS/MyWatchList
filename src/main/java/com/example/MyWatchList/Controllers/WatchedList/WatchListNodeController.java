package com.example.MyWatchList.Controllers.WatchedList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class WatchListNodeController {

    @FXML private HBox baseContainer;
    @FXML private Label titleLabel;
    @FXML private TextField moneyTxtField;
    @FXML private Button editButton;
    @FXML private Button deletButton;
    private int nodeNumber;
    private String labelText;

    public void initWatchListNode(String labelText, int nodeNumber) {
        this.labelText = labelText;
        this.nodeNumber = nodeNumber;
        setElements();
    }

    private void setElements(){
        titleLabel.setText(labelText);
    }



}
