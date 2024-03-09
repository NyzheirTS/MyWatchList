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

import java.io.IOException;
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
    KeyCombination kc = new KeyCodeCombination(KeyCode.ESCAPE, KeyCombination.SHIFT_DOWN);

    //Carousel Objects

    //Init Hub ????????????????????????????????????????????????????????????????????????????

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        api.fetchData(this::setMethods);
        menuAction();
    }

    private void setMethods(){
        TrendingCarouselModel trending = new TrendingCarouselModel(trendingMovieHbox, trendingTvHbox, forwardButtonTrendingMovie, forwardButtonTrendingTv, backButtonTrendingMovie, backButtonTrendingTv);
        TopRatedCarouselModel toprated = new TopRatedCarouselModel(topRatedMovieHbox, topRatedTvHbox, forwardButtonTopRatedMovie, forwardButtonTopRatedTv, backButtonTopRatedMovies, backButtonTopRatedTv);
        UpcomingCarouselModel upcoming = new UpcomingCarouselModel(upcomingMovieHbox, upcomingTvHbox, forwardButtonUpcomingMovie, forwardButtonUpcomingTv, backButtonMovieUpcoming, backButtonUpcomingTv);
        new HomePageController(trending, toprated, upcoming);
        new WatchedListController(pnItems, addNodeButton);
    }



    //Main Movement System.???????????????????????????????????????????????????

    public void handleClicks(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == btnOverview) {
            pnlHome.toFront();
        }
        if(actionEvent.getSource() == btnOrders) {
            pnlWatched.toFront();
        }
        if(actionEvent.getSource() == btnSettings){
            pnlSettings.toFront();
        }
    }


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

    public void setKeyListeners(Scene scene){
        scene.setOnKeyPressed(e -> {
            if (kc.match(e)) {
                if (menuCloseButton.isVisible()) menuClose.run();
                else menuOpen.run();
                e.consume();
            }
        });
    }

}