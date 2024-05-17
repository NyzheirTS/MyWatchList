package com.example.MyWatchList.AppEntry;

import com.example.MyWatchList.ApiClass.ApiConnection;
import com.example.MyWatchList.Controllers.HomePage.HomePageFactory;
import com.example.MyWatchList.Controllers.CommonComponent.EventRequest;
import com.example.MyWatchList.Controllers.InfoPage.InfoPageController;
import com.example.MyWatchList.Controllers.InfoPage.InfoPageFactory;
import com.example.MyWatchList.Controllers.SettingsPage.SettingsPageFactory;
import com.example.MyWatchList.Controllers.WatchedList.WatchedListFactory;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {

    @FXML private Button btnOverview;
    @FXML private Button btnOrders;
    @FXML private Button btnSettings;
    @FXML private HBox menuPnl;
    @FXML private BorderPane motherContainer;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        api.fetchData(this::setMethods);
    }

    private void setMethods() {
        infoPageBorderPane.setCenter(homePage);
        motherContainer.addEventFilter(Event.ANY, event -> {
            if (event.getEventType() == EventRequest.INFO_PAGE_REQUEST && (infoPage != null && infoPage.getProperties().containsKey("controller"))) {
                InfoPageController infoPageController = (InfoPageController) infoPage.getProperties().get("controller");
                try {
                    infoPageController.updatePage(((EventRequest) event).getNodeNumber(), ((EventRequest) event).getMedia_Type());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                pnlInfoPageToFront.run();
                event.consume();
            }
        });
    }

    public void clearAndSet(Node node){
        Platform.runLater(() -> {
            infoPageBorderPane.getChildren().clear();
            infoPageBorderPane.setCenter(node);
        });
    }

    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnOverview) pnlHomeToFront.run();
        if(actionEvent.getSource() == btnOrders) pnlWatchedToFront.run();
        if(actionEvent.getSource() == btnSettings) pnlSettingsToFront.run();
    }

    private final Runnable pnlHomeToFront = () -> clearAndSet(homePage);
    private final Runnable pnlSettingsToFront = () -> clearAndSet(settingsPage);
    private final Runnable pnlWatchedToFront = () -> clearAndSet(watchedList);
    private final Runnable pnlInfoPageToFront = () -> clearAndSet(infoPage);


    public void setSceneListeners(Scene scene) {
        scene.setOnKeyPressed(e -> {
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