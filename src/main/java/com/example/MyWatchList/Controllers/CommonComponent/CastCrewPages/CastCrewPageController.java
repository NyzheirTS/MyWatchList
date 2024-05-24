package com.example.MyWatchList.Controllers.CommonComponent.CastCrewPages;

import com.example.MyWatchList.Controllers.CommonComponent.CommonFactory;
import com.example.MyWatchList.DataModels.CommonModels.CreditsModel;
import com.example.MyWatchList.DataModels.CommonModels.MediaInfoPageModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class CastCrewPageController {
    @FXML private VBox castVbox;
    @FXML private VBox crewVbox;

    public void updatePage(MediaInfoPageModel jsonString){
        buildCastList(jsonString);
        buildCrewList(jsonString);
    }

    private void buildCastList(MediaInfoPageModel jsonString){
        castVbox.getChildren().clear();
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

    private void buildCrewList(MediaInfoPageModel jsonString){
        crewVbox.getChildren().clear();
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

