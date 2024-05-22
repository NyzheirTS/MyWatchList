package com.example.MyWatchList.Controllers.InfoPage;

import com.example.MyWatchList.Controllers.CommonComponent.CommonFactory;
import com.example.MyWatchList.DataModels.CommonModels.CreditsModel;
import com.example.MyWatchList.DataModels.CommonModels.MediaInfoPageModel;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FooterController {
    @FXML private HBox actorHbox;
    public void updateFooter(MediaInfoPageModel jsonString){
        buildHBox(jsonString);
    }

    private void buildHBox(MediaInfoPageModel jsonString){
        actorHbox.getChildren().clear();
        CreditsModel.Cast[] cast = jsonString.getCredits().getCast();
        for ( int i = 0; i <= 10; i++){
            VBox actorNode = CommonFactory.createActorPosterNode(
                    cast[i].getProfile_path(),
                    cast[i].getName(),
                    cast[i].getCharacter()
            );
            actorHbox.getChildren().addAll(actorNode);
        }
    }
}
