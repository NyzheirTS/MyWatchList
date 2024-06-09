package com.example.MyWatchList.AppEntry;

import com.example.MyWatchList.ApiClass.ApiConnection;
import com.example.MyWatchList.Caching.JsonCache;
import com.example.MyWatchList.Controllers.CommonComponent.CommonFactory;
import com.example.MyWatchList.Controllers.EventHandlers.CastCrewRequestEvent;
import com.example.MyWatchList.Controllers.HistoryManager.History;
import com.example.MyWatchList.Controllers.HistoryManager.UpdateCastCrewPageCommand;
import com.example.MyWatchList.Controllers.HistoryManager.UpdateInfoPageCommand;
import com.example.MyWatchList.Controllers.HomePage.HomePageFactory;
import com.example.MyWatchList.Controllers.EventHandlers.InfoPageRequestEvent;
import com.example.MyWatchList.Controllers.DynamicPages.InfoPageFactory;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {

    @FXML private Button historyBack;
    @FXML private Button btnOverview;
    @FXML private Button btnOrders;
    @FXML private Button btnSettings;
    @FXML private HBox menuPnl;
    @FXML private BorderPane motherContainer;
    @FXML private BorderPane infoPageBorderPane;

    private final VBox watchedList = WatchedListFactory.createWatchedList();
    private final VBox settingsPage = SettingsPageFactory.createSettingsPage();
    private final BorderPane homePage = HomePageFactory.createHomepage();
    private final BorderPane movieInfoPage = InfoPageFactory.createMovieInfoPage();
    private final BorderPane tvInfoPage = InfoPageFactory.createTvInfoPage();
    private final ScrollPane castCrewPage = CommonFactory.createCastCrewPage();
    private History history;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        history = new History(historyBack);
        ApiConnection.getInstance().fetchData(this::setMethods);
        activateButtons();
    }

    private void setMethods() {
        infoPageBorderPane.setCenter(homePage);
        motherContainer.addEventFilter(Event.ANY, event -> {
            try {
                if (event.getEventType() == InfoPageRequestEvent.MOVIE_PAGE_REQUEST && movieInfoPage != null) {
                    history.executeCommand(new UpdateInfoPageCommand(((InfoPageRequestEvent) event).getNodeNumber(), ((InfoPageRequestEvent) event).getMedia_Type(), pnlInfoPageToFront));
                    event.consume();
                } else if (event.getEventType() == InfoPageRequestEvent.TV_PAGE_REQUEST && tvInfoPage != null) {
                    history.executeCommand(new UpdateInfoPageCommand(((InfoPageRequestEvent) event).getNodeNumber(), ((InfoPageRequestEvent) event).getMedia_Type(), tvPnlToFront));
                    event.consume();
                } else if (event.getEventType() == CastCrewRequestEvent.CAST_CREW_PAGE_REQUEST && castCrewPage != null) {
                    history.executeCommand(new UpdateCastCrewPageCommand(((CastCrewRequestEvent) event).getString(), castCrewPageToFront));
                    event.consume();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
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

    private final Runnable pnlHomeToFront = () -> {
        clearAndSet(homePage);
        history.clearHistory();
    };
    private final Runnable pnlSettingsToFront = () -> {
        clearAndSet(settingsPage);
        history.clearHistory();
    };
    private final Runnable pnlWatchedToFront = () -> {
        clearAndSet(watchedList);
        history.clearHistory();
    };
    private final Runnable pnlInfoPageToFront = () -> clearAndSet(movieInfoPage);
    private final Runnable tvPnlToFront = () -> clearAndSet(tvInfoPage);
    private final Runnable castCrewPageToFront = () -> clearAndSet(castCrewPage);

    private void activateButtons(){
        historyBack.setOnAction(event -> history.goBack());
    }

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