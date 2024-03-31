package com.example.MyWatchList.Controllers.CommonComponent;

import com.example.MyWatchList.Controllers.HomePage.HomePageFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CommonFactory {
    public static AnchorPane createPosterNode (String text, int nodeNumber, Double score, String title, String mediaType) {
        try {
            FXMLLoader loader = new FXMLLoader(HomePageFactory.class.getResource("/com/example/MyWatchList/CommonFXML/poster-template.fxml"));
            AnchorPane posterNode = loader.load();

            PosterNodeController controller = loader.getController();
            controller.initPosterNode(text, nodeNumber, score, title, mediaType);
            posterNode.getProperties().put("posterController", controller);

            return posterNode;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static VBox createActorPosterNode(String urlKey, String actorName, String actorRole){
        try {
            FXMLLoader loader = new FXMLLoader(CommonFactory.class.getResource("/com/example/MyWatchList/CommonFXML/actor-poster.fxml"));
            VBox actorNode = loader.load();

            ActorPosterController controller = loader.getController();
            controller.initActorPoster(urlKey, actorName, actorRole);
            actorNode.getProperties().put("actorController", controller);

            return actorNode;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
