package com.example.MyWatchList.Controllers.CommonComponent;

import com.example.MyWatchList.Controllers.CommonComponent.CastCrewPages.CastCrewPageController;
import com.example.MyWatchList.Controllers.CommonComponent.CastCrewPages.CastItemController;
import com.example.MyWatchList.Controllers.CommonComponent.CastCrewPages.CrewItemController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;

public class CommonFactory {
    private static final String ComponentController = "controller";
    private CommonFactory(){}
    public static AnchorPane createPosterNode (String text, int nodeNumber, String title, String mediaType, boolean enableGrowEvents, boolean enableDropShadow, boolean enableTooltip) {
        try {
            FXMLLoader loader = new FXMLLoader(CommonFactory.class.getResource("/com/example/MyWatchList/CommonFXML/poster-template.fxml"));
            AnchorPane posterNode = loader.load();

            PosterNodeController controller = loader.getController();

            controller.setEnableGrowEvents(enableGrowEvents);
            controller.setEnableDropShadow(enableDropShadow);
            controller.setEnableTooltip(enableTooltip);
            controller.initPosterNode(text, nodeNumber, title, mediaType);

            posterNode.getProperties().put(ComponentController, controller);

            return posterNode;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static VBox createActorPosterNode(int id, String urlKey, String actorName, String actorRole){
        try {
            FXMLLoader loader = new FXMLLoader(CommonFactory.class.getResource("/com/example/MyWatchList/CommonFXML/actor-poster.fxml"));
            VBox actorNode = loader.load();

            PersonPosterController controller = loader.getController();

            controller.initActorPoster(id,urlKey, actorName, actorRole);

            actorNode.getProperties().put(ComponentController, controller);

            return actorNode;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static ScrollPane createCastCrewPage(){
        try{
            FXMLLoader loader = new FXMLLoader(CommonFactory.class.getResource("/com/example/MyWatchList/CommonFXML/CastCrewPages/cast-crew-page.fxml"));
            ScrollPane castcrewpage = loader.load();
            CastCrewPageController.setInstance(loader.getController());
            return castcrewpage;
        } catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static VBox createCastItem(String name, String character, int id){
        try {
            FXMLLoader loader = new FXMLLoader(CommonFactory.class.getResource("/com/example/MyWatchList/CommonFXML/CastCrewPages/cast-item.fxml"));
            VBox castItem = loader.load();

            CastItemController controller = loader.getController();
            controller.makeCastItem(name, character, id);

            castItem.getProperties().put(ComponentController, controller);

            return castItem;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static VBox createCrewItem(String name, String job, String department, int id){
        try {
            FXMLLoader loader = new FXMLLoader(CommonFactory.class.getResource("/com/example/MyWatchList/CommonFXML/CastCrewPages/crew-item.fxml"));
            VBox crewItem = loader.load();

            CrewItemController controller = loader.getController();
            controller.createCrewItem(name, job, department, id);

            crewItem.getProperties().put(ComponentController, controller);

            return crewItem;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static ImageView createEmbedYoutube(String key){
        try {
            FXMLLoader loader = new FXMLLoader(CommonFactory.class.getResource("/com/example/MyWatchList/InfoPage/youtube-embed.fxml"));
            ImageView embedObject = loader.load();

            YoutubeEmbedController controller = loader.getController();
            controller.initEmbedController(key);

            embedObject.getProperties().put(ComponentController, controller);

            return embedObject;
        } catch ( IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static AnchorPane createLogo(String url, URL image){
        try{
            FXMLLoader loader = new FXMLLoader(CommonFactory.class.getResource("/com/example/MyWatchList/CommonFXML/logo-image.fxml"));
            AnchorPane logo = loader.load();

            LogoImageController controller = loader.getController();
            controller.initObject(url, image);

            logo.getProperties().put(ComponentController, controller);

            return logo;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static VBox createPopOver(String content, String createdAt, String updatedAt, String userName){
        try {
            FXMLLoader loader = new FXMLLoader(CommonFactory.class.getResource("/com/example/MyWatchList/InfoPage/review-popover.fxml"));
            VBox popOverObject = loader.load();

            ReviewPopOverController controller = loader.getController();
            controller.initPopOver(content, createdAt, updatedAt, userName);

            popOverObject.getProperties().put(ComponentController, controller);

            return popOverObject;
        } catch ( IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static HBox createReviews(String authorName, String userName, double rating){
        try {
            FXMLLoader loader = new FXMLLoader(CommonFactory.class.getResource("/com/example/MyWatchList/InfoPage/review.fxml"));
            HBox reviewObject = loader.load();

            ReviewsController controller = loader.getController();

            controller.initReviews(authorName, userName, rating);

            reviewObject.getProperties().put(ComponentController, controller);

            return reviewObject;
        } catch ( IOException e){
            e.printStackTrace();
            return null;
        }
    }

}
