package com.example.MyWatchList.Controllers.DynamicPages.PersonPage;

import com.example.MyWatchList.DataModels.PersonModels.PersonModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class PersonLeftPanelController {

    @FXML private Label knownFor;
    @FXML private Label dateOfBirth;
    @FXML private Label dateOfDeath;
    @FXML private VBox alsoKnownAs;
    @FXML private Label homePage;


    private static PersonLeftPanelController instance;

    public void update(PersonModel string){
        setLabels(string);
        buildVbox(string.getAlso_known_as());
    }

    private void setLabels(PersonModel string){
        Platform.runLater(() -> {
            knownFor.setText(string.getKnown_for_department() != null ? string.getKnown_for_department() : "N/A");
            dateOfBirth.setText(string.getBirthday() != null ? string.getBirthday() : "N/A");
            dateOfDeath.setText(string.getDeathday() != null ? string.getDeathday() : "N/A");
            homePage.setText(string.getHomepage() != null ? string.getHomepage() : "N/A");
        });
    }

    private void buildVbox(String[] arr){
        alsoKnownAs.getChildren().clear();
        if (arr.length == 0){
            alsoKnownAs.getChildren().add(formattedLabel("N/A"));
        } else {
            for (String str : arr) {
                alsoKnownAs.getChildren().add(formattedLabel(str));
            }
        }
    }

    private Label formattedLabel(String name){
        Label label = new Label(name);
        label.setFont(new Font("Arimo", 20));
        return label;
    }

    public static PersonLeftPanelController getInstance() {return PersonLeftPanelController.instance;}
    public static void setInstance(PersonLeftPanelController instance) {
        PersonLeftPanelController.instance = instance;}
}
