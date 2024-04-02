package com.example.MyWatchList.Controllers.InfoPage;

import com.example.MyWatchList.Controllers.CommonComponent.CommonFactory;
import com.example.MyWatchList.DataModels.CommonModels.CreditsModel;
import com.example.MyWatchList.DataModels.CommonModels.MediaInfoPageModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FooterPanelController {
    @FXML
    private HBox actorHbox;

    private MediaInfoPageModel jsonString;
    public void initFooter(MediaInfoPageModel jsonString){
        this.jsonString = jsonString;
        buildHBox();
    }

    private void buildHBox(){
        Platform.runLater(() -> {
            CreditsModel.Cast[] cast = jsonString.getCredits().getCast();
            for ( int i = 0; i <= 10; i++){
                VBox actorNode = CommonFactory.createActorPosterNode(
                        cast[i].getProfile_path(),
                        cast[i].getName(),
                        cast[i].getCharacter()
                );
                actorHbox.getChildren().addAll(actorNode);
            }
        });
    }
}
