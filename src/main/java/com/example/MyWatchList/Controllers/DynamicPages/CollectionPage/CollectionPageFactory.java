package com.example.MyWatchList.Controllers.DynamicPages.CollectionPage;

import com.example.MyWatchList.Controllers.CommonComponent.CommonFactory;
import com.example.MyWatchList.Controllers.DynamicPages.PersonPage.PersonInfoCardController;
import com.example.MyWatchList.Controllers.DynamicPages.PersonPage.PersonPageController;
import com.example.MyWatchList.Controllers.DynamicPages.PersonPage.PersonPageFactory;
import com.example.MyWatchList.DataModels.CollectionModels.CollectionModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class CollectionPageFactory {
    private CollectionPageFactory(){}

    public static BorderPane createCollectionPage(){
        try{
            FXMLLoader loader = new FXMLLoader(CollectionPageFactory.class.getResource("/com/example/MyWatchList/CollectionPage/collection-page.fxml"));
            BorderPane page = loader.load();
            CollectionPageController.setInstance(loader.getController());
            return page;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static StackPane createCollectionCard(CollectionModel.Parts data){
        try {
            FXMLLoader loader = new FXMLLoader(CommonFactory.class.getResource("/com/example/MyWatchList/CollectionPage/collection-card.fxml"));
            StackPane card = loader.load();

            CollectionCardController controller = loader.getController();
            controller.createCard(data);
            card.getProperties().put("controller", controller);

            return card;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
