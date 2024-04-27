package com.example.MyWatchList.Controllers.HomePage;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import java.io.IOException;


public class HomePageFactory {
    private HomePageFactory(){}
    public static HBox createCarousel () {
        try {
            FXMLLoader loader = new FXMLLoader(HomePageFactory.class.getResource("/com/example/MyWatchList/HomePage/carousel-template.fxml"));
            HBox carousel = loader.load();

            CarouselController controller = loader.getController();
            controller.initCarousel();
            carousel.getProperties().put("controller", controller);

            return carousel;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static BorderPane createHomepage(){
        try {
            FXMLLoader loader = new FXMLLoader(HomePageFactory.class.getResource("/com/example/MyWatchList/HomePage/homepage-template.fxml"));
            BorderPane homePage = loader.load();

            HomePageController controller = loader.getController();
            controller.initHomePage();
            homePage.getProperties().put("controller", controller);

            return homePage;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
