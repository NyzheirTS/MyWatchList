package com.example.MyWatchList.Controllers.DynamicPages.SearchPage;

import com.example.MyWatchList.Controllers.CommonComponent.CommonFactory;
import com.example.MyWatchList.Controllers.DynamicPages.PersonPage.PersonInfoCardController;
import com.example.MyWatchList.DataModels.SearchModels.SearchModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class SearchPageFactory {
    private SearchPageFactory(){}
    private static final String ComponentController = "controller";

    public static BorderPane createSearchPage(){
        try{
            FXMLLoader loader = new FXMLLoader(SearchPageFactory.class.getResource("/com/example/MyWatchList/SearchPage/search-page.fxml"));
            BorderPane page = loader.load();
            SearchPageController.setInstance(loader.getController());
            SearchPageController.getInstance().initMethods();
            return page;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static BorderPane createMediaInfoCard(String img, String name, String overview, double score, int id, String mediaType){
        try {
            FXMLLoader loader = new FXMLLoader(CommonFactory.class.getResource("/com/example/MyWatchList/SearchPage/media-search-panel.fxml"));
            BorderPane infoCard = loader.load();

            MediaSearchCardController controller = loader.getController();
            controller.createCard(img, name, overview, score, id, mediaType);
            infoCard.getProperties().put(ComponentController, controller);

            return infoCard;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static BorderPane createPersonInfoCard(String img, String name, SearchModel.Results.KnownFor[] knownFor, int id){
        try {
            FXMLLoader loader = new FXMLLoader(CommonFactory.class.getResource("/com/example/MyWatchList/SearchPage/person-search-panel.fxml"));
            BorderPane infoCard = loader.load();

            PersonSearchCardController controller = loader.getController();
            controller.createCard(img, name, knownFor, id);
            infoCard.getProperties().put(ComponentController, controller);

            return infoCard;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
