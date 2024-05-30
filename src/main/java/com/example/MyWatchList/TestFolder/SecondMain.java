package com.example.MyWatchList.TestFolder;

import com.example.MyWatchList.AppEntry.Main;
import com.example.MyWatchList.AppEntry.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SecondMain extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        //starting stage
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/MyWatchList/CommonFXML/common-scrollpane-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1100, 800);

        //CastCrewPageController controller = fxmlLoader.getController();
        //YoutubeEmbedController.setHostServices(getHostServices());
        //controller.makepage();

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
