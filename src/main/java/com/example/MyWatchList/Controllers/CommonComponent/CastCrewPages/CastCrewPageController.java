package com.example.MyWatchList.Controllers.CommonComponent.CastCrewPages;

import com.example.MyWatchList.Controllers.CommonComponent.CommonFactory;
import com.example.MyWatchList.DataModels.CommonModels.CreditsModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class CastCrewPageController {
    @FXML private VBox castVbox;
    @FXML private VBox crewVbox;

    private static CastCrewPageController instance;

    public void update(CreditsModel jsonString){
        buildCastList(jsonString);
        buildCrewList(jsonString);
    }

    private void buildCastList(CreditsModel jsonString){
        castVbox.getChildren().clear();
        CreditsModel.Cast[] cast = jsonString.getCast();
        for(CreditsModel.Cast casts : cast){
            VBox castItem = CommonFactory.createCastItem(
                    casts.getName(),
                    casts.getCharacter(),
                    casts.getId()
            );
            Platform.runLater(() -> castVbox.getChildren().add(castItem));
        }
    }

    private void buildCrewList(CreditsModel jsonString){
        crewVbox.getChildren().clear();
        CreditsModel.Crew[] crew = jsonString.getCrew();
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

    public static CastCrewPageController getInstance(){
        return CastCrewPageController.instance;
    }

    public static void setInstance(CastCrewPageController instance){CastCrewPageController.instance = instance;}
}

