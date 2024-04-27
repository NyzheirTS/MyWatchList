package com.example.MyWatchList.AppEntry;

import com.example.MyWatchList.Controllers.InfoPage.YoutubeEmbedController;
import com.example.MyWatchList.Controllers.SettingsPage.SettingsPageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //starting stage
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-fxml.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1100, 800);

        MainController controller = fxmlLoader.getController();
        //YoutubeEmbedController.setHostServices(getHostServices());
        controller.setSceneListeners(scene);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }



    public static void main(String[] args) {
        launch();
    }

}