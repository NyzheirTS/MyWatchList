package com.example.MyWatchList.Controllers.InfoPage;

import com.example.MyWatchList.Controllers.CommonComponent.PosterNodeController;
import com.example.MyWatchList.Controllers.SettingsPage.SettingsPageController;
import com.example.MyWatchList.Controllers.SettingsPage.SettingsPageFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class InfoPageFactory {

    public static BorderPane createInfoPage() {
        try {
            FXMLLoader loader = new FXMLLoader(InfoPageFactory.class.getResource("/com/example/MyWatchList/InfoPage/info-page.fxml"));
            BorderPane infoPage = loader.load();
            InfoPageController controller = loader.getController();
            infoPage.getProperties().put("controller", controller);  // Store controller in properties
            return infoPage;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static PosterNodeController createPosterNodeController(InfoPageController infoPageController) {
        PosterNodeController posterNodeController = new PosterNodeController();
        posterNodeController.setInfoPageController(infoPageController);
        // Additional configuration if needed
        return posterNodeController;
    }
}
