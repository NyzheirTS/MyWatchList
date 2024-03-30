package com.example.MyWatchList.Controllers.InfoPage;

import com.example.MyWatchList.Controllers.HomePage.HomePageFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


import java.io.IOException;

public class InfoPageFactory {

    public static BorderPane createInfoPage (int MediaID, String MediaType) {
        try {
            FXMLLoader loader = new FXMLLoader(HomePageFactory.class.getResource("/com/example/MyWatchList/InfoPage/info-page.fxml"));
            BorderPane infoPage = loader.load();

            InfoPageController controller = loader.getController();
            controller.initInfoPage(MediaID, MediaType);

            infoPage.getProperties().put("infoPageController", controller);

            return infoPage;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static AnchorPane createMovieHeader(String jsonString){
        try {
            FXMLLoader loader = new FXMLLoader(HomePageFactory.class.getResource("/com/example/MyWatchList/InfoPage/movie-header.fxml"));
            AnchorPane movieHeader = loader.load();

            MovieHeaderController controller = loader.getController();
            controller.initMovieHeader(jsonString);

            movieHeader.getProperties().put("movieHeaderController", controller);

            return movieHeader;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
