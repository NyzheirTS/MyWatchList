package com.example.MyWatchList.AppEntry;

import com.example.MyWatchList.ApiClass.ApiConnection;
import com.example.MyWatchList.Controllers.HomePage.HomePageFactory;
import com.example.MyWatchList.Controllers.InfoPage.InfoPageRequestEvent;
import com.example.MyWatchList.Controllers.WatchedList.WatchedListController;
import com.example.MyWatchList.TestFolder.TestJsonStringHolder;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {

    @FXML
    private VBox pnlHome;
    @FXML
    private AnchorPane pnlWatched;
    @FXML
    private AnchorPane pnlSettings;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnSettings;
    @FXML
    private AnchorPane menuPnl;
    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private Button menuCloseButton;
    @FXML
    private Button menuOpenButton;
    @FXML
    private BorderPane infoPageBorderPane;

    //Watched List Section
    @FXML
    private Button addNodeButton; //this will be removed later when working on list section follow new design see HomePage for examples.
    @FXML
    private VBox pnItems;

    private final BorderPane homepage = HomePageFactory.createHomepage();


    //Other Variables
    ApiConnection api = new ApiConnection();



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        api.fetchData(this::setMethods);
        menuAction();
    }

    private void setMethods() {
        mainBorderPane.addEventFilter(InfoPageRequestEvent.INFO_PAGE_REQUEST, event -> {
            infoPageBorderPane.toFront();
            infoPageBorderPane.setCenter(event.getBorderPane());
            //VBox.setVgrow(event.getBorderPane(), Priority.ALWAYS);
        });
        pnlHome.getChildren().add(homepage);
        new WatchedListController(pnItems, addNodeButton);
    }



    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnOverview) pnlHomeToFront.run();
        if(actionEvent.getSource() == btnOrders) pnlWatchedToFront.run();
        if(actionEvent.getSource() == btnSettings) pnlSettingsToFront.run();
    }



    Runnable pnlHomeToFront = () -> {
        pnlHome.toFront();
        menuOpenButton.toFront();
    };
    Runnable pnlSettingsToFront = () -> {
        pnlSettings.toFront();
        menuOpenButton.toFront();
    };
    Runnable pnlWatchedToFront = () -> {
        pnlWatched.toFront();
        menuOpenButton.toFront();
    };


    Runnable menuClose = () -> {
        menuOpenButton.toFront();

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(menuPnl.prefWidthProperty(), 0);
        KeyFrame kf = new KeyFrame(Duration.millis(200), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();

        menuCloseButton.setVisible(false);

        timeline.setOnFinished(e -> {
            menuCloseButton.setVisible(false);
            menuOpenButton.setVisible(true);
            mainBorderPane.setLeft(menuPnl);
        });
    };


    Runnable menuOpen = () -> {
        menuPnl.setPrefWidth(0);
        mainBorderPane.setLeft(menuPnl);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(menuPnl.prefWidthProperty(), 246);
        KeyFrame kf = new KeyFrame(Duration.millis(200), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();

        menuOpenButton.setVisible(false);
        timeline.setOnFinished(event -> {
            menuCloseButton.setVisible(true);
            menuOpenButton.setVisible(false);
        });
    };

    private void menuAction(){
        menuCloseButton.setOnAction(event -> menuClose.run());
        menuOpenButton.setOnAction(event -> menuOpen.run());
    }

    public void setSceneListeners(Scene scene){
        scene.setOnKeyPressed(e -> {
            if (ShortCuts.shiftEsc.match(e)) {
                if (menuCloseButton.isVisible()) menuClose.run();
                else menuOpen.run();
                e.consume();
            }
            if (ShortCuts.shift1.match(e)){
                pnlHomeToFront.run();
                e.consume();
            }
            if (ShortCuts.shift2.match(e)){
                pnlWatchedToFront.run();
                e.consume();
            }
            if (ShortCuts.shift5.match(e)){
                pnlSettingsToFront.run();
                e.consume();
            }
        });
    }


}