package com.example.MyWatchList.Controllers.InfoPage;

import com.example.MyWatchList.AppConfig.AppCleaner;
import com.example.MyWatchList.Controllers.CommonComponent.CommonFactory;
import com.example.MyWatchList.DataModels.CommonModels.CreditsModel;
import com.example.MyWatchList.DataModels.CommonModels.MediaInfoPageModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FooterPanelController implements AppCleaner {
    @FXML
    private HBox actorHbox;

    private MediaInfoPageModel jsonString;
    public void initFooter(MediaInfoPageModel jsonString){
        this.jsonString = jsonString;
        buildHBox();
    }

    private void buildHBox(){
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

    @Override
    public void cleanup() {
        for (Node child : actorHbox.getChildren()) {
            if (child instanceof VBox){
                Object controller = child.getProperties().get("controller");
                if (controller instanceof AppCleaner){
                    ((AppCleaner)controller).cleanup();
                }
            }
        }
        actorHbox.getChildren().clear();
        jsonString = null;
        //System.out.println("Footer Cleaned");
    }
}
