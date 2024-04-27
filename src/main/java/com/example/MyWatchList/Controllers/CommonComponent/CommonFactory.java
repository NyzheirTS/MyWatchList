package com.example.MyWatchList.Controllers.CommonComponent;

import com.example.MyWatchList.Controllers.CommonComponent.CastCrewPages.CastCrewPageController;
import com.example.MyWatchList.Controllers.CommonComponent.CastCrewPages.CastItemController;
import com.example.MyWatchList.Controllers.CommonComponent.CastCrewPages.CrewItemController;
import com.example.MyWatchList.DataModels.CommonModels.MediaInfoPageModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CommonFactory {
    private static final String ComponentController = "controller";
    private CommonFactory(){}
    public static AnchorPane createPosterNode (String text, int nodeNumber, String title, String mediaType, boolean enableGrowEvents, boolean enableDropShadow, boolean enableTooltip) {
        try {
            FXMLLoader loader = new FXMLLoader(CommonFactory.class.getResource("/com/example/MyWatchList/CommonFXML/poster-template.fxml"));
            AnchorPane posterNode = loader.load();

            PosterNodeController controller = loader.getController();
            controller.cleanup();

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

    public static VBox createActorPosterNode(String urlKey, String actorName, String actorRole){
        try {
            FXMLLoader loader = new FXMLLoader(CommonFactory.class.getResource("/com/example/MyWatchList/CommonFXML/actor-poster.fxml"));
            VBox actorNode = loader.load();

            ActorPosterController controller = loader.getController();
            controller.cleanup();

            controller.initActorPoster(urlKey, actorName, actorRole);

            actorNode.getProperties().put(ComponentController, controller);

            return actorNode;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static HBox createCastCrewPage(MediaInfoPageModel jsonString){
        try{
            FXMLLoader loader = new FXMLLoader(CommonFactory.class.getResource("/com/example/MyWatchList/CommonFXML/CastCrewPages/cast-crew-page.fxml"));
            HBox castcrewpage = loader.load();

            CastCrewPageController controller = loader.getController();
            controller.initPage(jsonString);

            castcrewpage.getProperties().put(ComponentController, controller);

            return castcrewpage;
        } catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static BorderPane createCommonScrollPane(Node node){
        try {
            FXMLLoader loader = new FXMLLoader(CommonFactory.class.getResource("/com/example/MyWatchList/CommonFXML/common-scrollpane-page.fxml"));
            BorderPane commonScrollPane = loader.load();

            CommonScrollPaneController controller = loader.getController();
            controller.makePage(node);

            commonScrollPane.getProperties().put(ComponentController, controller);

            return commonScrollPane;

        } catch (IOException e){
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

}
