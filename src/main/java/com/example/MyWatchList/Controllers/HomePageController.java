package com.example.MyWatchList.Controllers;

import com.example.MyWatchList.APIClasses.ApiConnection;
import com.example.MyWatchList.DataClasses.MovieDetails;
import com.example.MyWatchList.DataClasses.TvDetails;
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

    static final int numOfItemsVis = 5;
    private int currPageMoive = 0;
    private int currPageTv = 0;
    private final List<Node> movieNodes = new ArrayList<>();
    private final List<Node> tvNodes = new ArrayList<>();

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
        setMovementMovieWeek();
        setMovementTvWeek();
    }


    private void initArrays() {
        Platform.runLater(() -> {
            //Trending Movie Week Array
            movieTrendingWeekArray();
            //Trending Tv Week Array
            tvTrendingWeekArray();
        });
    }

    private void updateDisplay(HBox hBox, List<Node> list, int currPage) {
        hBox.getChildren().clear();
        int start = currPage * numOfItemsVis;
        int end = Math.min(start + numOfItemsVis, list.size());
        for(int i = start; i < end; i++) {
            Node node = list.get(i);
            Platform.runLater(()-> hBox.getChildren().addAll(node));
        }
    }

    private void navigation(int direction, HBox hBox, List<Node> list, boolean isMovie) {
        int currPage = isMovie ? currPageMoive : currPageTv;
        int totalPages = (int) Math.ceil((double)list.size() / numOfItemsVis);
        currPage += direction;
        if (currPage < 0) currPage = 0;
        else if (currPage >= totalPages) currPage = totalPages - 1;

        if (isMovie) {
            currPageMoive = currPage; // Update the movie page tracker
        } else {
            currPageTv = currPage; // Update the TV show page tracker
        }

        updateDisplay(hBox, list, currPage);
    }

    private void setMovementMovieWeek(){
        fwordButton.setOnAction(event -> navigation(1, homeHbox, movieNodes, true));
        bwordButton.setOnAction(event -> navigation(-1, homeHbox, movieNodes, true));
    }

    private void setMovementTvWeek(){
        fwordButtonTV.setOnAction(event -> navigation(1, homeHBoxTV, tvNodes, false));
        bwordButtonTV.setOnAction(event -> navigation(-1, homeHBoxTV, tvNodes, false));
    }

    private void movieTrendingWeekArray(){
        try {
            String jsonResponse = ApiConnection.getResponseData(ApiConnection.ApiCallType.MOVIE_TRENDING_WEEK);
            MovieDetails[] movies = MovieDetails.fromJson(jsonResponse);

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
                movieNodes.add(node.getsNode());
            }
            updateDisplay(homeHbox, movieNodes, currPageMoive);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private  void tvTrendingWeekArray(){
        try {
            String jsonResponseTV = ApiConnection.getResponseData(ApiConnection.ApiCallType.TV_TRENDING_WEEK);
            TvDetails[] tvs = TvDetails.fromJson(jsonResponseTV);
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
                tvNodes.add(node.getsNode());
            }
            updateDisplay(homeHBoxTV, tvNodes, currPageTv);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
