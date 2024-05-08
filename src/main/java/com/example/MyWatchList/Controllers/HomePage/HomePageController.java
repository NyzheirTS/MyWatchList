package com.example.MyWatchList.Controllers.HomePage;

import com.example.MyWatchList.ApiClass.ApiConnection;
import com.example.MyWatchList.Controllers.CommonComponent.CommonFactory;
import com.example.MyWatchList.DataModels.*;
import com.example.MyWatchList.DataModels.MovieModels.MoviePosterModel;
import com.example.MyWatchList.DataModels.TvModels.TvPosterModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;



public class HomePageController {
    @FXML
    public BorderPane staticpage;
    @FXML
    private  VBox trendingPane;
    @FXML
    private  Button trendingPaneButton;
    @FXML
    private  VBox upcomingPane;
    @FXML
    private  Button upcomingPaneButton;
    @FXML
    private  VBox topRatedPane;
    @FXML
    private  Button topRatedPaneButton;
    @FXML
    private  HBox trendingTvContainer;
    @FXML
    private  HBox trendingMovieContainer;
    @FXML
    private  HBox upcomingTvContainer;
    @FXML
    private  HBox upcomingMovieContainer;
    @FXML
    private  HBox topRatedTvContainer;
    @FXML
    private  HBox topRatedMovieContainer;


    public void initHomePage(){
        homePageNavControl();
        setTrendingCarousel(true);
    }


    private boolean trendingLoaded = false;
    public void setTrendingCarousel(boolean state){
        if (!trendingLoaded){
            Platform.runLater(() -> {
                try {
                    movieTrendingWeekArray();
                    tvTrendingArray();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                trendingLoaded = state;
            });
        }
    }

    private boolean upcomingLoaded = false;
    public void setUpcomingCarousel(boolean state){
        if(!upcomingLoaded) {
            Platform.runLater(() -> {
                try {
                    movieUpcomingArray();
                    tvUpcomingArray();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                upcomingLoaded = state;
            });
        }
    }

    private boolean topratedLoaded = false;
    public void setTopratedCarousel(boolean state){
        if (!topratedLoaded) {
            Platform.runLater(() -> {
                try {
                    movieTopRatedArray();
                    tvTopRatedArray();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                topratedLoaded = state;
            });
        }
    }




    private void movieTrendingWeekArray() throws IOException {
        String jsonResponse = ApiConnection.getResponseData(ApiCallType.MOVIE_TRENDING_WEEK);
        MoviePosterModel[] movies = MoviePosterModel.fromJson(jsonResponse);
        final HBox trendingCarouselMovie = getCarouselMovie(HomePageFactory.createCarousel(), movies);
       trendingMovieContainer.getChildren().add(trendingCarouselMovie);
    }

    private void movieUpcomingArray() throws IOException {
        String jsonResponse = ApiConnection.getResponseData(ApiCallType.MOVIE_UPCOMING);
        MoviePosterModel[] movieUpcoming = MoviePosterModel.fromJson(jsonResponse);
        final HBox upcomginCarouselMovie = getCarouselMovie(HomePageFactory.createCarousel(), movieUpcoming);
        upcomingMovieContainer.getChildren().add(upcomginCarouselMovie);
    }

    private void movieTopRatedArray() throws IOException {
        String jsonResponse = ApiConnection.getResponseData(ApiCallType.MOVIE_TOPRATED);
        MoviePosterModel[] movieTopRated = MoviePosterModel.fromJson(jsonResponse);
        final HBox topRatedCarouselMovie = getCarouselMovie(HomePageFactory.createCarousel(), movieTopRated);
        topRatedMovieContainer.getChildren().add(topRatedCarouselMovie);

    }

    private  void tvTrendingArray() throws IOException {
        String jsonResponse = ApiConnection.getResponseData(ApiCallType.TV_TRENDING_WEEK);
        TvPosterModel[] tvs = TvPosterModel.fromJson(jsonResponse);
        final HBox trendingCarouselTv = getCarouselTV(HomePageFactory.createCarousel(), tvs);
        trendingTvContainer.getChildren().add(trendingCarouselTv);

    }

    private void tvUpcomingArray() throws IOException {
        String jsonResponse = ApiConnection.getResponseData(ApiCallType.TV_UPCOMING);
        TvPosterModel[] tvUpcoming = TvPosterModel.fromJson(jsonResponse);
        final HBox upcomingCarouselTv = getCarouselTV(HomePageFactory.createCarousel(), tvUpcoming);
        upcomingTvContainer.getChildren().add(upcomingCarouselTv);
    }

    private void tvTopRatedArray() throws IOException {
        String jsonResponse = ApiConnection.getResponseData(ApiCallType.TV_TOPRATED);
        TvPosterModel[] tvTopRated = TvPosterModel.fromJson(jsonResponse);
        final HBox topratedCarouselTv = getCarouselTV(HomePageFactory.createCarousel(), tvTopRated);
        topRatedTvContainer.getChildren().add(topratedCarouselTv);
    }

    private void homePageNavControl(){
        topRatedPaneButton.setOnAction(event -> {
            setTopratedCarousel(true);
            topRatedPane.toFront();
        });
        upcomingPaneButton.setOnAction(event -> {
            setUpcomingCarousel(true);
            upcomingPane.toFront();
        });
        trendingPaneButton.setOnAction(event -> trendingPane.toFront());
    }


    @NotNull
    private HBox getCarouselMovie(HBox movieCarousel, MoviePosterModel[] movies) {
        for (MoviePosterModel movie : movies) {
            AnchorPane posterNode = CommonFactory.createPosterNode(
                    movie.getPosterPath(),
                    movie.getId(),
                    movie.getTitle(),
                    movie.getMedia_type(),
                    true,
                    true,
                    true
            );
                getControllerFromCarousel(movieCarousel).addItem(posterNode);
        }
        getControllerFromCarousel(movieCarousel).updateDisplay();
        return movieCarousel;
    }

    @NotNull
    private HBox getCarouselTV(HBox tvCarousel, TvPosterModel[] tvs) {
        for (TvPosterModel tv : tvs) {
            AnchorPane posterNode = CommonFactory.createPosterNode(
                    tv.getPoster_path(),
                    tv.getId(),
                    tv.getName(),
                    tv.getMedia_type(),
                    true,
                    true,
                    true
            );
            getControllerFromCarousel(tvCarousel).addItem(posterNode);
        }
        getControllerFromCarousel(tvCarousel).updateDisplay();
        return tvCarousel;
    }

    private CarouselController getControllerFromCarousel(HBox node){
        return (CarouselController) node.getProperties().get("controller");
    }
}