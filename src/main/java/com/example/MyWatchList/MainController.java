package com.example.MyWatchList;

import com.example.MyWatchList.ApiClass.ApiConnection;
import com.example.MyWatchList.InfoPage.InfoPageRequestEvent;
import com.example.MyWatchList.WatchedList.WatchedListController;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {

    //Main Scene Controls
    @FXML
    public VBox pnlHome;
    @FXML
    public AnchorPane pnlWatched;
    @FXML
    public AnchorPane pnlSettings;
    @FXML
    public Button btnOverview;
    @FXML
    public Button btnOrders;
    @FXML
    public Button btnSettings;
    @FXML
    public AnchorPane menuPnl;
    @FXML
    public BorderPane mainBorderPane;
    @FXML
    public Button menuCloseButton;
    @FXML
    public Button menuOpenButton;
    @FXML
    public BorderPane infoPageBorderPane;

    //Watched List Section
    @FXML
    public Button addNodeButton;
    @FXML
    public VBox pnItems;

    private BorderPane homepage = ComponentFactory.createHomepage();


    //Other Variables
    ApiConnection api = new ApiConnection();



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        api.fetchData(this::setMethods);
        menuAction();
    }

    private void setMethods() {
        mainBorderPane.addEventFilter(InfoPageRequestEvent.INFO_PAGE_REQUEST, event -> infoPageBorderPane.toFront());
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