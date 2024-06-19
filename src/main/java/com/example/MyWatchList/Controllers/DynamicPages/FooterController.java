package com.example.MyWatchList.Controllers.DynamicPages;

import com.example.MyWatchList.Controllers.CommonComponent.CommonFactory;
import com.example.MyWatchList.DataModels.CommonModels.CreditsModel;
import com.example.MyWatchList.DataModels.CommonModels.MediaInfoPageModel;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FooterController {
    @FXML private HBox actorHbox;
    public void updateFooter(CreditsModel jsonString){
        buildHBox(jsonString);
    }

    private void buildHBox(CreditsModel jsonString) {
        actorHbox.getChildren().clear();
        CreditsModel.Cast[] cast = jsonString.getCast();
        try {
            for (int i = 0; i <= 10; i++) {
                VBox actorNode = CommonFactory.createActorPosterNode(
                        cast[i].getId(),
                        cast[i].getProfile_path(),
                        cast[i].getName(),
                        cast[i].getCharacter()
                );
                actorHbox.getChildren().addAll(actorNode);
            }
        } catch (ArrayIndexOutOfBoundsException ignore){
            //exception is ignored
        }
    }
}
