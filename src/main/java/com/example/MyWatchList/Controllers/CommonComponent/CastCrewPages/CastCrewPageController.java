package com.example.MyWatchList.Controllers.CommonComponent.CastCrewPages;

import com.example.MyWatchList.Controllers.CommonComponent.CommonFactory;
import com.example.MyWatchList.DataModels.CommonModels.CreditsModel;
import com.example.MyWatchList.DataModels.CommonModels.MediaInfoPageModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.skin.VirtualFlow;
import javafx.scene.layout.VBox;

public class CastCrewPageController {
    @FXML private VBox castVbox;
    @FXML private VBox crewVbox;

    private MediaInfoPageModel jsonString;
    public void initPage(MediaInfoPageModel jsonString){
        this.jsonString = jsonString;
        buildCastList();
        buildCrewList();
    }

    private void buildCastList(){
        CreditsModel.Cast[] cast = jsonString.getCredits().getCast();
        for(CreditsModel.Cast casts : cast){
            VBox castItem = CommonFactory.createCastItem(
                    casts.getName(),
                    casts.getCharacter(),
                    casts.getId()
            );
            Platform.runLater(() -> castVbox.getChildren().add(castItem));
        }
    }

    private void buildCrewList(){
        CreditsModel.Crew[] crew = jsonString.getCredits().getCrew();
        for(CreditsModel.Crew crews : crew){
            VBox castItem = CommonFactory.createCrewItem(
                    crews.getName(),
                    crews.getJob(),
                    crews.getDepartment(),
                    crews.getId()
            );
            Platform.runLater(() -> crewVbox.getChildren().add(castItem));
        }
    }
}
