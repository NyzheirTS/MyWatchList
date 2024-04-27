package com.example.MyWatchList.Controllers.InfoPage;

import com.example.MyWatchList.Controllers.InfoPage.MovieInfoPage.MovieHeaderController;
import com.example.MyWatchList.Controllers.InfoPage.MovieInfoPage.MovieLeftPanelController;
import com.example.MyWatchList.DataModels.CommonModels.MediaInfoPageModel;
import com.example.MyWatchList.DataModels.MovieModels.MovieInfoPageModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;


import java.io.IOException;

public class InfoPageFactory {
     private static final String ComponentController = "controller";
     private InfoPageFactory(){}

    public static BorderPane createInfoPage (Integer MediaID, String MediaType) {
        try {
            FXMLLoader loader = new FXMLLoader(InfoPageFactory.class.getResource("/com/example/MyWatchList/InfoPage/info-page.fxml"));
            BorderPane infoPage = loader.load();

            InfoPageController controller = loader.getController();
            controller.cleanup();

            controller.initInfoPage(MediaID, MediaType);

            infoPage.getProperties().put(ComponentController, controller);


            return infoPage;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static HBox createMovieHeader(MovieInfoPageModel jsonString){
        try {
            FXMLLoader loader = new FXMLLoader(InfoPageFactory.class.getResource("/com/example/MyWatchList/InfoPage/MovieInfoPage/movie-header.fxml"));
            HBox movieHeader = loader.load();

            MovieHeaderController controller = loader.getController();
            controller.cleanup();

            controller.initMovieHeader(jsonString);

            movieHeader.getProperties().put(ComponentController, controller);

            return movieHeader;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static VBox createMovieLeftPanel(MovieInfoPageModel jsonString){
        try {
            FXMLLoader loader = new FXMLLoader(InfoPageFactory.class.getResource("/com/example/MyWatchList/InfoPage/MovieInfoPage/movie-left-panel.fxml"));
            VBox movieLeftPanel = loader.load();

            MovieLeftPanelController controller = loader.getController();
            controller.cleanup();

            controller.initLeftPanel(jsonString);

            movieLeftPanel.getProperties().put(ComponentController, controller);

            return movieLeftPanel;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static HBox createFooter(MediaInfoPageModel jsonString){
        try {
            FXMLLoader loader = new FXMLLoader(InfoPageFactory.class.getResource("/com/example/MyWatchList/InfoPage/footer.fxml"));
            HBox footer = loader.load();

            FooterPanelController controller = loader.getController();
            controller.cleanup();

            controller.initFooter(jsonString);

            footer.getProperties().put(ComponentController, controller);

            return footer;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static VBox createRightPanel(MediaInfoPageModel jsonString, String MediaType){
        try {
            FXMLLoader loader = new FXMLLoader(InfoPageFactory.class.getResource("/com/example/MyWatchList/InfoPage/right-panel.fxml"));
            VBox rightPanel = loader.load();

            RightPanelController controller = loader.getController();
            controller.cleanup();

            controller.initRightPanel(jsonString, MediaType);

            rightPanel.getProperties().put(ComponentController, controller);

            return  rightPanel;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static VBox createMiddlePanel(MediaInfoPageModel jsonString){
        try {
            FXMLLoader loader = new FXMLLoader(InfoPageFactory.class.getResource("/com/example/MyWatchList/InfoPage/middle-panel.fxml"));
            VBox middlePanel = loader.load();

            MiddlePanelController controller = loader.getController();
            controller.cleanup();

            controller.initMiddlePanel(jsonString);

            middlePanel.getProperties().put(ComponentController, controller);

            return middlePanel;
        } catch ( IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static ImageView createEmbedYoutube(String key){
        try {
            FXMLLoader loader = new FXMLLoader(InfoPageFactory.class.getResource("/com/example/MyWatchList/InfoPage/youtube-embed.fxml"));
            ImageView embedObject = loader.load();

            YoutubeEmbedController controller = loader.getController();
            controller.cleanup();

            controller.initEmbedController(key);

            embedObject.getProperties().put(ComponentController, controller);

            return embedObject;
        } catch ( IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static HBox createReviews(String authorName, String userName, int rating){
        try {
            FXMLLoader loader = new FXMLLoader(InfoPageFactory.class.getResource("/com/example/MyWatchList/InfoPage/review.fxml"));
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

    public static VBox createPopOver(String content, String createdAt, String updatedAt, String userName){
        try {
            FXMLLoader loader = new FXMLLoader(InfoPageFactory.class.getResource("/com/example/MyWatchList/InfoPage/review-popover.fxml"));
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




}
