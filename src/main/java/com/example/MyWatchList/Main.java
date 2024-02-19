package com.example.MyWatchList;

import com.example.MyWatchList.Controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private MainController mainController;

    @Override
    public void start(Stage stage) throws IOException {
        //starting stage
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-fxml.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1100, 800);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

    public static void main(String[] args) {launch();}
}