package com.example.MyWatchList.Controllers.InfoPage;

import com.example.MyWatchList.Controllers.CommonComponent.CommonFactory;
import com.example.MyWatchList.DataModels.CreditsModel;
import com.example.MyWatchList.DataModels.MovieModels.MovieInfoPageModel;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FooterController {
    public HBox actorHbox;

    private MovieInfoPageModel jsonString;
    public void initFooter(MovieInfoPageModel jsonString){
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
}
