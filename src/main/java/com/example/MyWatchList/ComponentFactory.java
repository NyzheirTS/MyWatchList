package com.example.MyWatchList;

import com.example.MyWatchList.HomePage.CarouselController;
import com.example.MyWatchList.HomePage.HomePageController;
import com.example.MyWatchList.HomePage.PosterNodeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import java.io.IOException;


public class ComponentFactory {

    public static Node createPosterNode (String text, int nodeNumber, Double score, String title, String mediaType) {
        try {
            FXMLLoader loader = new FXMLLoader(ComponentFactory.class.getResource("/com/example/MyWatchList/HomePage/poster-template.fxml"));
            Node posterNode = loader.load();

            PosterNodeController controller = loader.getController();
            controller.initPosterNode(text, nodeNumber, score, title, mediaType);
            posterNode.getProperties().put("controller", controller);

            return posterNode;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static HBox createCarousel () {
        try {
            FXMLLoader loader = new FXMLLoader(ComponentFactory.class.getResource("/com/example/MyWatchList/HomePage/carousel-template.fxml"));
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
            FXMLLoader loader = new FXMLLoader(ComponentFactory.class.getResource("/com/example/MyWatchList/HomePage/homepage-template.fxml"));
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
