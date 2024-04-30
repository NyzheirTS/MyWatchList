package com.example.MyWatchList.AppEntry;

import com.example.MyWatchList.ApiClass.ApiConnection;
import com.example.MyWatchList.Controllers.HomePage.HomePageFactory;
import com.example.MyWatchList.Controllers.CommonComponent.EventRequest;
import com.example.MyWatchList.Controllers.InfoPage.InfoPageController;
import com.example.MyWatchList.Controllers.InfoPage.InfoPageFactory;
import com.example.MyWatchList.Controllers.SettingsPage.SettingsPageFactory;
import com.example.MyWatchList.Controllers.WatchedList.WatchedListFactory;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {

    @FXML private Button forwardTestButton;
    @FXML private Button backTestButon;
    @FXML private Button btnOverview;
    @FXML private Button btnOrders;
    @FXML private Button btnSettings;
    @FXML private HBox menuPnl;
    @FXML private BorderPane mainBorderPane;
    @FXML private Button menuCloseButton;
    @FXML private Button menuOpenButton;
    @FXML private BorderPane infoPageBorderPane;


    //TODO: work on style look for themes and such
    //TODO: work on optimization for info-page and maybe lazy load them in history most important to unload images while not seen
    //TODO: fuck the infopage mem leak there dont know what to do kms



    //Static Variables ??
    ApiConnection api = new ApiConnection();
    private final VBox watchedList = WatchedListFactory.createWatchedList();
    private final VBox settingsPage = SettingsPageFactory.createSettingsPage();
    private final BorderPane homePage = HomePageFactory.createHomepage();
    private final BorderPane infoPage = InfoPageFactory.createInfoPage();
    PageHistoryManager pageHistoryManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pageHistoryManager = new PageHistoryManager(infoPageBorderPane, backTestButon, forwardTestButton);
        api.fetchData(this::setMethods);
        menuAction();
    }

    private void setMethods() {
        infoPageBorderPane.setCenter(homePage);
        mainBorderPane.addEventFilter(Event.ANY, event -> {
            if (event.getEventType() == EventRequest.INFO_PAGE_REQUEST) {
                handleInfoPageRequest(event);
            }
        });
    }

    private void handleInfoPageRequest(Event event) {
        if (infoPage != null && infoPage.getProperties().containsKey("controller")) {
            InfoPageController infoPageController = (InfoPageController) infoPage.getProperties().get("controller");
            infoPageController.updatepage(((EventRequest) event).getNodeNumber(), ((EventRequest)event).getMedia_type());
            pageHistoryManager.navigateTo(infoPage);
            event.consume();
        }
    }



    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnOverview) pnlHomeToFront.run();
        if(actionEvent.getSource() == btnOrders) pnlWatchedToFront.run();
        if(actionEvent.getSource() == btnSettings) pnlSettingsToFront.run();
    }




    Runnable pnlHomeToFront = () -> pageHistoryManager.navigateTo(homePage);
    Runnable pnlSettingsToFront = () -> pageHistoryManager.navigateTo(settingsPage);
    Runnable pnlWatchedToFront = () -> pageHistoryManager.navigateTo(watchedList);


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
            mainBorderPane.setTop(menuPnl);
        });
    };


    Runnable menuOpen = () -> {
        menuPnl.setPrefWidth(0);
        mainBorderPane.setTop(menuPnl);

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
        menuClose.run();
        menuCloseButton.setOnAction(event -> menuClose.run());
        menuOpenButton.setOnAction(event -> menuOpen.run());
        backTestButon.setOnAction(event -> pageHistoryManager.goBack());
        forwardTestButton.setOnAction(event -> pageHistoryManager.goForward());
    }


    public void setSceneListeners(Scene scene) {
        scene.setOnKeyPressed(e -> {
            if (ShortCuts.shiftEsc.match(e)) {
                if (menuCloseButton.isVisible()) menuClose.run();
                else menuOpen.run();
                e.consume();
            }
            if (ShortCuts.shift1.match(e)) {
                pnlHomeToFront.run();
                e.consume();
            }
            if (ShortCuts.shift2.match(e)) {
                pnlWatchedToFront.run();
                e.consume();
            }
            if (ShortCuts.shift5.match(e)) {
                pnlSettingsToFront.run();
                e.consume();
            }
        });
    }
}