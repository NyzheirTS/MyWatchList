package com.example.MyWatchList.Controllers.DynamicPages;

import com.example.MyWatchList.Controllers.DynamicPages.MovieInfoPages.*;
import com.example.MyWatchList.Controllers.DynamicPages.TvInfoPages.TvInfoPageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class InfoPageFactory {
    private static final String ComponentController = "controller";
    private InfoPageFactory(){}

    public static BorderPane createMovieInfoPage() {
        try {
            FXMLLoader loader = new FXMLLoader(InfoPageFactory.class.getResource("/com/example/MyWatchList/InfoPage/MoviePages/movie-info-page.fxml"));
            BorderPane infoPage = loader.load();
            MovieInfoPageController.setInstance(loader.getController());
            MovieInfoPageController.getInstance().initMethods();
            return infoPage;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static BorderPane createTvInfoPage() {
        try {
            FXMLLoader loader = new FXMLLoader(InfoPageFactory.class.getResource("/com/example/MyWatchList/InfoPage/TVPages/tv-info-page.fxml"));
            BorderPane infoPage = loader.load();
            TvInfoPageController.setInstance(loader.getController());
            TvInfoPageController.getInstance().initMethods();
            return infoPage;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static VBox createRightPanel() {
        try {
            FXMLLoader loader = new FXMLLoader(InfoPageFactory.class.getResource("/com/example/MyWatchList/InfoPage/right-panel.fxml"));
            VBox rightPanel = loader.load();
            RightPanelController controller = loader.getController();
            rightPanel.getProperties().put(ComponentController, controller);  // Store controller in properties
            return rightPanel;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static HBox createHeader(){
        try {
            FXMLLoader loader = new FXMLLoader(InfoPageFactory.class.getResource("/com/example/MyWatchList/InfoPage/movie-header.fxml"));
            HBox movieHeader = loader.load();
            HeaderController controller = loader.getController();
            movieHeader.getProperties().put(ComponentController, controller);
            return movieHeader;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static VBox createLeftPanel(){
        try {
            FXMLLoader loader = new FXMLLoader(InfoPageFactory.class.getResource("/com/example/MyWatchList/InfoPage/movie-left-panel.fxml"));
            VBox leftPanel = loader.load();
            LeftPanelController controller = loader.getController();
            leftPanel.getProperties().put(ComponentController, controller);
            return leftPanel;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static HBox createFooter(){
        try {
            FXMLLoader loader = new FXMLLoader(InfoPageFactory.class.getResource("/com/example/MyWatchList/InfoPage/footer.fxml"));
            HBox footer = loader.load();
            FooterController controller = loader.getController();
            footer.getProperties().put(ComponentController, controller);
            return footer;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static VBox createMiddlePanel(){
        try {
            FXMLLoader loader = new FXMLLoader(InfoPageFactory.class.getResource("/com/example/MyWatchList/InfoPage/middle-panel.fxml"));
            VBox middlePanel = loader.load();
            MiddlePanelController controller = loader.getController();
            middlePanel.getProperties().put(ComponentController, controller);
            return middlePanel;
        } catch ( IOException e){
            e.printStackTrace();
            return null;
        }
    }




}
