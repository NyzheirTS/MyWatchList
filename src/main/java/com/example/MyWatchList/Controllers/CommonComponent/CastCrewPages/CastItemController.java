package com.example.MyWatchList.Controllers.CommonComponent.CastCrewPages;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CastItemController {
    @FXML private VBox castItem;
    @FXML private Label nameLabel;
    @FXML private Label characterLabel;
    private int id;

    public void makeCastItem(String name, String character, int id){
        nameLabel.setText(name);
        characterLabel.setText(character);
        this.id = id;
        castItem.setCursor(Cursor.HAND);
    }
}
