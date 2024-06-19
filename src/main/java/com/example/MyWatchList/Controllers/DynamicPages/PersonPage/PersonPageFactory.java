package com.example.MyWatchList.Controllers.DynamicPages.PersonPage;

import com.example.MyWatchList.Controllers.CommonComponent.CommonFactory;
import com.example.MyWatchList.Controllers.CommonComponent.PosterNodeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Date;

public class PersonPageFactory {
    private PersonPageFactory(){}
    private static final String ComponentController = "controller";

    public static BorderPane createActorActressPage(){
        try{
            FXMLLoader loader = new FXMLLoader(PersonPageFactory.class.getResource("/com/example/MyWatchList/ActorActressPage/person-page.fxml"));
            BorderPane page = loader.load();
            PersonPageController.setInstance(loader.getController());
            PersonPageController.getInstance().initPage();
            return page;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static HBox createActorActressHeader(){
        try{
            FXMLLoader loader = new FXMLLoader(PersonPageFactory.class.getResource("/com/example/MyWatchList/ActorActressPage/person-page-header.fxml"));
            HBox header = loader.load();
            PersonHeaderController.setInstance(loader.getController());
            return header;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static VBox createPersonLeftPanel(){
        try{
            FXMLLoader loader = new FXMLLoader(PersonPageFactory.class.getResource("/com/example/MyWatchList/ActorActressPage/person-page-leftpanel.fxml"));
            VBox left = loader.load();
            PersonLeftPanelController.setInstance(loader.getController());
            return left;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static BorderPane createPersonMiddlePanel(){
        try{
            FXMLLoader loader = new FXMLLoader(PersonPageFactory.class.getResource("/com/example/MyWatchList/ActorActressPage/person-page-middelpanel.fxml"));
            BorderPane middle = loader.load();
            PersonMiddleController.setInstance(loader.getController());
            PersonMiddleController.getInstance().initContainer();
            return middle;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static HBox createPersonInfoCard(String img, String title, String character, String datetime, Double rate, int id, String media_type){
        try {
            FXMLLoader loader = new FXMLLoader(CommonFactory.class.getResource("/com/example/MyWatchList/ActorActressPage/person-page-info-card.fxml"));
            HBox infoCard = loader.load();

            PersonInfoCardController controller = loader.getController();
            controller.createCard(img, title, character, datetime, rate, id, media_type);
            infoCard.getProperties().put(ComponentController, controller);

            return infoCard;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
