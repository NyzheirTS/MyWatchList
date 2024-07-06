package com.example.MyWatchList.AppEntry;

import com.example.MyWatchList.ApiClass.ApiConnection;
import com.example.MyWatchList.Controllers.CommonComponent.CommonFactory;
import com.example.MyWatchList.Controllers.DynamicPages.CollectionPage.CollectionPageFactory;
import com.example.MyWatchList.Controllers.DynamicPages.PersonPage.PersonPageFactory;
import com.example.MyWatchList.Controllers.DynamicPages.SearchPage.SearchPageController;
import com.example.MyWatchList.Controllers.DynamicPages.SearchPage.SearchPageFactory;
import com.example.MyWatchList.Controllers.EventHandlers.CollectionPageRequestEvent;
import com.example.MyWatchList.Controllers.EventHandlers.PersonPageRequestEvent;
import com.example.MyWatchList.Controllers.EventHandlers.CastCrewRequestEvent;
import com.example.MyWatchList.Controllers.HistoryManager.*;
import com.example.MyWatchList.Controllers.HomePage.HomePageFactory;
import com.example.MyWatchList.Controllers.EventHandlers.InfoPageRequestEvent;
import com.example.MyWatchList.Controllers.DynamicPages.MovieInfoPages.InfoPageFactory;
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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {

    @FXML private TextField searchField;
    @FXML private ImageView searchButton;
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
    private final BorderPane personPage = PersonPageFactory.createActorActressPage();
    private final BorderPane collectionPage = CollectionPageFactory.createCollectionPage();
    private final BorderPane searchPage = SearchPageFactory.createSearchPage();
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
                    history.executeCommand(new UpdateMoviePageCommand(((InfoPageRequestEvent) event).getNodeNumber(), moviePnlToFront));
                    event.consume();
                } else if (event.getEventType() == InfoPageRequestEvent.TV_PAGE_REQUEST && tvInfoPage != null) {
                    history.executeCommand(new UpdateMoviePageCommand(((InfoPageRequestEvent) event).getNodeNumber(), tvPnlToFront));
                    event.consume();
                } else if (event.getEventType() == CastCrewRequestEvent.CAST_CREW_PAGE_REQUEST && castCrewPage != null) {
                    history.executeCommand(new UpdateCastCrewPageCommand(((CastCrewRequestEvent) event).getString(), castCrewPageToFront));
                    event.consume();
                } else if (event.getEventType() == PersonPageRequestEvent.ACTOR_ACTRESS_PAGE_REQUEST && personPage != null){
                    history.executeCommand(new UpdatePersonPageCommand(((PersonPageRequestEvent) event).getId(), personPageToFront));
                    event.consume();
                } else if (event.getEventType() == CollectionPageRequestEvent.COLLECTION_PAGE_REQUEST && collectionPage != null) {
                    history.executeCommand(new UpdateCollectionPageCommand(((CollectionPageRequestEvent) event).getId(), collectionPageToFront));
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

    private final Runnable pnlHomeToFront = () ->{
        clearAndSet(homePage);
        history.clearHistory();
    };
    private final Runnable pnlSettingsToFront = () ->{
        clearAndSet(settingsPage);
        history.clearHistory();
    };
    private final Runnable pnlWatchedToFront = () ->{
        clearAndSet(watchedList);
        history.clearHistory();
    };
    private final Runnable moviePnlToFront = () -> clearAndSet(movieInfoPage);
    private final Runnable tvPnlToFront = () -> clearAndSet(tvInfoPage);
    private final Runnable castCrewPageToFront = () -> clearAndSet(castCrewPage);
    private final Runnable personPageToFront = () -> clearAndSet(personPage);
    private final Runnable collectionPageToFront = () -> clearAndSet(collectionPage);
    private final Runnable searchPageToFront = () -> clearAndSet(searchPage);

    private void activateButtons(){
        historyBack.setOnAction(event -> history.goBack());
        setSearchButtonOnAction();
        setSearchFieldOnAction();
    }

    private void setSearchFieldOnAction(){
        searchField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER && !searchField.getText().isEmpty()){
                try {
                    SearchPageController.getInstance().update(searchField.getText());
                    searchPageToFront.run();
                    motherContainer.requestFocus();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void setSearchButtonOnAction(){
        searchButton.setOnMouseClicked(event -> {
            if (!searchField.getText().isEmpty()) {
                try {
                    SearchPageController.getInstance().update(searchField.getText());
                    searchPageToFront.run();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
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