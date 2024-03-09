package com.example.MyWatchList.HomePage;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class TrendingCarouselModel {
    HBox trendingMovieHbox;
    HBox trendingTvHbox;
    Button backButtonMovieTrending;
    Button forwardButtonMovieTrending;
    Button forwardButtonTvTrending;
    Button backButtonTVTrending;

    public TrendingCarouselModel(HBox trendingMovieHbox, HBox trendingTvHbox, Button forwardButtonMovieTrending, Button forwardButtonTvTrending, Button backButtonMovieTrending, Button backButtonTVTrending){
        this.trendingMovieHbox = trendingMovieHbox;
        this.trendingTvHbox = trendingTvHbox;
        this.forwardButtonMovieTrending = forwardButtonMovieTrending;
        this.forwardButtonTvTrending = forwardButtonTvTrending;
        this.backButtonMovieTrending = backButtonMovieTrending;
        this.backButtonTVTrending = backButtonTVTrending;
    }
    public HBox getTrendingMovieHbox() {
        return trendingMovieHbox;
    }

    public void setTrendingMovieHbox(HBox trendingMovieHbox) {
        this.trendingMovieHbox = trendingMovieHbox;
    }

    public HBox getTrendingTvHbox() {
        return trendingTvHbox;
    }

    public void setTrendingTvHbox(HBox trendingTvHbox) {
        this.trendingTvHbox = trendingTvHbox;
    }

    public Button getBackButtonMovieTrending() {
        return backButtonMovieTrending;
    }

    public void setBackButtonMovieTrending(Button backButtonMovieTrending) {
        this.backButtonMovieTrending = backButtonMovieTrending;
    }

    public Button getForwardButtonMovieTrending() {
        return forwardButtonMovieTrending;
    }

    public void setForwardButtonMovieTrending(Button forwardButtonMovieTrending) {
        this.forwardButtonMovieTrending = forwardButtonMovieTrending;
    }

    public Button getForwardButtonTvTrending() {
        return forwardButtonTvTrending;
    }

    public void setForwardButtonTvTrending(Button forwardButtonTvTrending) {
        this.forwardButtonTvTrending = forwardButtonTvTrending;
    }

    public Button getBackButtonTVTrending() {
        return backButtonTVTrending;
    }

    public void setBackButtonTVTrending(Button backButtonTVTrending) {
        this.backButtonTVTrending = backButtonTVTrending;
    }
}
