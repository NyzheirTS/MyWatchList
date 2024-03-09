package com.example.MyWatchList.HomePage;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class TopRatedCarouselModel {
    HBox topRatedMovieHbox;
    HBox topRatedTvHbox;
    Button backButtonTopRatedMovies;
    Button forwardButtonTopRatedMovie;
    Button backButtonTopRatedTv;
    Button forwardButtonTopRatedTv;

    public TopRatedCarouselModel(HBox topRatedMovieHbox, HBox topRatedTvHbox, Button forwardButtonTopRatedMovie, Button forwardButtonTopRatedTv, Button backButtonTopRatedMovies, Button backButtonTopRatedTv){
        this.topRatedMovieHbox = topRatedMovieHbox;
        this.topRatedTvHbox = topRatedTvHbox;
        this.forwardButtonTopRatedMovie = forwardButtonTopRatedMovie;
        this.forwardButtonTopRatedTv = forwardButtonTopRatedTv;
        this.backButtonTopRatedMovies = backButtonTopRatedMovies;
        this.backButtonTopRatedTv = backButtonTopRatedTv;
    }

    public HBox getTopRatedMovieHbox() {
        return topRatedMovieHbox;
    }

    public void setTopRatedMovieHbox(HBox topRatedMovieHbox) {
        this.topRatedMovieHbox = topRatedMovieHbox;
    }

    public HBox getTopRatedTvHbox() {
        return topRatedTvHbox;
    }

    public void setTopRatedTvHbox(HBox topRatedTvHbox) {
        this.topRatedTvHbox = topRatedTvHbox;
    }

    public Button getBackButtonTopRatedMovies() {
        return backButtonTopRatedMovies;
    }

    public void setBackButtonTopRatedMovies(Button backButtonTopRatedMovies) {
        this.backButtonTopRatedMovies = backButtonTopRatedMovies;
    }

    public Button getForwardButtonTopRatedMovie() {
        return forwardButtonTopRatedMovie;
    }

    public void setForwardButtonTopRatedMovie(Button forwardButtonTopRatedMovie) {
        this.forwardButtonTopRatedMovie = forwardButtonTopRatedMovie;
    }

    public Button getBackButtonTopRatedTv() {
        return backButtonTopRatedTv;
    }

    public void setBackButtonTopRatedTv(Button backButtonTopRatedTv) {
        this.backButtonTopRatedTv = backButtonTopRatedTv;
    }

    public Button getForwardButtonTopRatedTv() {
        return forwardButtonTopRatedTv;
    }

    public void setForwardButtonTopRatedTv(Button forwardButtonTopRatedTv) {
        this.forwardButtonTopRatedTv = forwardButtonTopRatedTv;
    }
}
