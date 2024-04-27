package com.example.MyWatchList.Controllers.CommonComponent.CastCrewPages;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CrewItemController {
    @FXML private VBox crewItem;
    @FXML private Label nameLabel;
    @FXML private Label jobLabel;
    @FXML private Label departmentLabel;
    private int id;

    public void createCrewItem(String name, String job, String department, int id){
        nameLabel.setText(name);
        jobLabel.setText(job);
        departmentLabel.setText(department);
        this.id = id;
        crewItem.setCursor(Cursor.HAND);
    }

}
