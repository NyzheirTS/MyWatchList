package com.example.MyWatchList;

import com.example.MyWatchList.ApiClass.ApiConnection;
import com.example.MyWatchList.WatchedList.WatchedListController;
import com.example.MyWatchList.HomePage.HomePageController;
import com.example.MyWatchList.HomePage.TopRatedCarouselModel;
import com.example.MyWatchList.HomePage.TrendingCarouselModel;
import com.example.MyWatchList.HomePage.UpcomingCarouselModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {

    //Main Scene Controls
    @FXML
    public BorderPane pnlHome;
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

    //Watched List Section
    @FXML
    public Button addNodeButton;
    @FXML
    public VBox pnItems;


    ////////////////////////////////////////// HomePage Section ////////////////////////////////////////////////////////
    //Trending Home Pane Section
    @FXML
    public VBox trendingPane;
    @FXML
    public Button forwardButtonTrendingTv;
    @FXML
    public Button backButtonTrendingTv;
    @FXML
    public Button forwardButtonTrendingMovie;
    @FXML
    public Button backButtonTrendingMovie;
    @FXML
    public HBox trendingMovieHbox;
    @FXML
    public HBox trendingTvHbox;
    @FXML
    public Button trendingPaneButton;
    //Upcoming Home Pane Section
    @FXML
    public VBox upcomingPane;
    @FXML
    public Button upcomingPaneButton;
    @FXML
    public HBox upcomingTvHbox;
    @FXML
    public HBox upcomingMovieHbox;
    @FXML
    public Button forwardButtonUpcomingMovie;
    @FXML
    public Button backButtonMovieUpcoming;
    @FXML
    public Button backButtonUpcomingTv;
    @FXML
    public Button forwardButtonUpcomingTv;
    //Top Rated Home Pane Section
    @FXML
    public VBox topRatedPane;
    @FXML
    public Button topRatedPaneButton;
    @FXML
    public HBox topRatedMovieHbox;
    @FXML
    public HBox topRatedTvHbox;
    @FXML
    public Button backButtonTopRatedMovies;
    @FXML
    public Button forwardButtonTopRatedMovie;
    @FXML
    public Button backButtonTopRatedTv;
    @FXML
    public Button forwardButtonTopRatedTv;


    //Other Variables
    ApiConnection api = new ApiConnection();
    HomePageController homeController;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        api.fetchData(this::setMethods);
        menuAction();
        homePageNavControl();
    }

    private void setMethods(){
        TrendingCarouselModel trending = new TrendingCarouselModel(trendingMovieHbox, trendingTvHbox, forwardButtonTrendingMovie, forwardButtonTrendingTv, backButtonTrendingMovie, backButtonTrendingTv);
        TopRatedCarouselModel toprated = new TopRatedCarouselModel(topRatedMovieHbox, topRatedTvHbox, forwardButtonTopRatedMovie, forwardButtonTopRatedTv, backButtonTopRatedMovies, backButtonTopRatedTv);
        UpcomingCarouselModel upcoming = new UpcomingCarouselModel(upcomingMovieHbox, upcomingTvHbox, forwardButtonUpcomingMovie, forwardButtonUpcomingTv, backButtonMovieUpcoming, backButtonUpcomingTv);

        homeController = new HomePageController(trending, toprated, upcoming);
        new WatchedListController(pnItems, addNodeButton);
    }



    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnOverview) pnlHomeToFront.run();
        if(actionEvent.getSource() == btnOrders) pnlWatchedToFront.run();
        if(actionEvent.getSource() == btnSettings) pnlSettingsToFront.run();
    }

    private void homePageNavControl(){
        topRatedPaneButton.setOnAction(event -> {
            homeController.setTopratedCarousel(true);
            topRatedPane.toFront();
        });
        upcomingPaneButton.setOnAction(event -> {
            homeController.setUpcomingCarousel(true);
            upcomingPane.toFront();
        });
        trendingPaneButton.setOnAction(event -> trendingPane.toFront());
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
        menuPnl.setPrefWidth(0);
        mainBorderPane.setLeft(menuPnl);
        menuOpenButton.toFront();
        menuCloseButton.setVisible(false);
        menuOpenButton.setVisible(true);
    };

    Runnable menuOpen = () -> {
        menuPnl.setPrefWidth(246);
        mainBorderPane.setLeft(menuPnl);
        menuCloseButton.setVisible(true);
        menuOpenButton.setVisible(false);
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