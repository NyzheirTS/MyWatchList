package com.example.MyWatchList.Controllers.InfoPage;

import com.example.MyWatchList.Controllers.InfoPage.MovieInfoPage.MovieHeaderController;
import com.example.MyWatchList.Controllers.InfoPage.MovieInfoPage.MovieLeftPanelController;
import com.example.MyWatchList.DataModels.CommonModels.MediaInfoPageModel;
import com.example.MyWatchList.DataModels.MovieModels.MovieInfoPageModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.*;


import java.io.IOException;

public class InfoPageFactory {

    public static BorderPane createInfoPage (int MediaID, String MediaType) {
        try {
            FXMLLoader loader = new FXMLLoader(InfoPageFactory.class.getResource("/com/example/MyWatchList/InfoPage/info-page.fxml"));
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

    public static HBox createMovieHeader(MovieInfoPageModel jsonString){
        try {
            FXMLLoader loader = new FXMLLoader(InfoPageFactory.class.getResource("/com/example/MyWatchList/InfoPage/MovieInfoPage/movie-header.fxml"));
            HBox movieHeader = loader.load();

            MovieHeaderController controller = loader.getController();
            controller.initMovieHeader(jsonString);

            movieHeader.getProperties().put("movieHeaderController", controller);

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
            controller.initLeftPanel(jsonString);

            movieLeftPanel.getProperties().put("movieLeftPanelController", controller);

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
            controller.initFooter(jsonString);

            footer.getProperties().put("footerController", controller);

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
            controller.initRightPanel(jsonString, MediaType);

            rightPanel.getProperties().put("rightPanelController", controller);

            return  rightPanel;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }


}
