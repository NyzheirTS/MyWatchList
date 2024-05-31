package com.example.MyWatchList.AppEntry;

import com.example.MyWatchList.Caching.JsonCache;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //starting stage
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-fxml.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1100, 800);
        MainController controller = fxmlLoader.getController();
        controller.setSceneListeners(scene);

        stage.setTitle("MyShowList");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
        stage.setOnCloseRequest(windowEvent -> {
            try {
                JsonCache.clearCacheOnShutdown(); //clear json cache when closing the app
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }



    public static void main(String[] args) {
        launch();
    }

}