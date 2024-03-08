package com.example.MyWatchList.Controllers;

import com.example.MyWatchList.APIClasses.ApiConnection;
import com.example.MyWatchList.DataClasses.MovieDetails;
import com.example.MyWatchList.DataClasses.TvDetails;
import com.example.MyWatchList.NodeClasses.Carousel;
import com.example.MyWatchList.NodeClasses.HomePageNode;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class HomePageController {

    //TODO: Removed the extendablecard want to move it to a dedicated page also change the way the carousel loads instead of all at once load as needed elements ass needed.
    //TODO: build the poster nodes and add them to an array then load them into the carousel as needed.
    HBox homeHbox;
    HBox homeHBoxTV;
    Button bwordButton;
    Button fwordButton;
    Button fwordButtonTV;
    Button bwordButtonTV;

    HomePageController(HBox homeHbox, Button bButton, Button fButton, Button tvfButton, Button tvbButton, HBox homeHBoxTV){
        this.homeHbox = homeHbox;
        this.bwordButton = bButton;
        this.fwordButton = fButton;
        this.fwordButtonTV = tvfButton;
        this.bwordButtonTV = tvbButton;
        this.homeHBoxTV = homeHBoxTV;
        initArrays();
    }



    private void initArrays() {
        Platform.runLater(() -> {
            movieTrendingWeekArray();
            tvTrendingWeekArray();
        });
    }



    private void movieTrendingWeekArray(){
        try {
            String jsonResponse = ApiConnection.getResponseData(ApiConnection.ApiCallType.MOVIE_TRENDING_WEEK);
            MovieDetails[] movies = MovieDetails.fromJson(jsonResponse);
            Carousel movieCarousel = new Carousel(homeHbox);

            for (MovieDetails movie : movies) {
                HomePageNode node = new HomePageNode(
                        movie.getPosterPath(),
                        movie.getId(),
                        movie.getOverview(),
                        movie.getBackdrop_path(),
                        movie.getTitle(),
                        movie.getVote_average(),
                        movie.getVote_count()
                );
                movieCarousel.addItem(node.getsNode());
            }
            movieCarousel.updateDisplay();
            fwordButton.setOnAction(event -> movieCarousel.navigate(1));
            bwordButton.setOnAction(event -> movieCarousel.navigate(-1));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private  void tvTrendingWeekArray(){
        try {
            String jsonResponseTV = ApiConnection.getResponseData(ApiConnection.ApiCallType.TV_TRENDING_WEEK);
            TvDetails[] tvs = TvDetails.fromJson(jsonResponseTV);
            Carousel tvCarousel = new Carousel(homeHBoxTV);

            for(TvDetails tv: tvs){
                HomePageNode node = new HomePageNode(
                        tv.getPoster_path(),
                        tv.getId(),
                        tv.getOverview(),
                        tv.getBackdrop_path(),
                        tv.getName(),
                        tv.getVote_average(),
                        tv.getVote_count()
                );
                tvCarousel.addItem(node.getsNode());
            }
            tvCarousel.updateDisplay();
            fwordButtonTV.setOnAction(event -> tvCarousel.navigate(1));
            bwordButtonTV.setOnAction(event -> tvCarousel.navigate(-1));
        } catch (Exception e){
            e.printStackTrace();
        }
    }


}
