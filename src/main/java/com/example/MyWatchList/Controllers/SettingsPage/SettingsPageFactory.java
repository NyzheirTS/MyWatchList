package com.example.MyWatchList.Controllers.SettingsPage;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class SettingsPageFactory {
    private SettingsPageFactory(){
    }

    public static VBox createSettingsPage(){
        try {
            FXMLLoader loader = new FXMLLoader(SettingsPageFactory.class.getResource("/com/example/MyWatchList/SettingsPage/settings-page.fxml"));
            VBox settingsPage = loader.load();

            SettingsPageController controller = loader.getController();
            controller.initSettingsPage();

            settingsPage.getProperties().put("controller", controller);

            return settingsPage;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
