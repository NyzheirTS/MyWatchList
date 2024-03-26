package com.example.MyWatchList.HomePage;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class UpcomingCarouselView {
    HBox upcomingTvHbox;
    HBox upcomingMovieHbox;
    Button forwardButtonUpcomingMovie;
    Button backButtonMovieUpcoming;
    Button backButtonUpcomingTv;
    Button forwardButtonUpcomingTv;

    public UpcomingCarouselView(HBox upcomingMovieHbox, HBox upcomingTvHbox, Button forwardButtonUpcomingMovie, Button forwardButtonUpcomingTv, Button backButtonMovieUpcoming, Button backButtonUpcomingTv){
        this.upcomingMovieHbox = upcomingMovieHbox;
        this.upcomingTvHbox = upcomingTvHbox;
        this.forwardButtonUpcomingMovie = forwardButtonUpcomingMovie;
        this.forwardButtonUpcomingTv = forwardButtonUpcomingTv;
        this.backButtonMovieUpcoming = backButtonMovieUpcoming;
        this.backButtonUpcomingTv = backButtonUpcomingTv;
    }

    public HBox getUpcomingTvHbox() {
        return upcomingTvHbox;
    }

    public void setUpcomingTvHbox(HBox upcomingTvHbox) {
        this.upcomingTvHbox = upcomingTvHbox;
    }

    public HBox getUpcomingMovieHbox() {
        return upcomingMovieHbox;
    }

    public void setUpcomingMovieHbox(HBox upcomingMovieHbox) {
        this.upcomingMovieHbox = upcomingMovieHbox;
    }

    public Button getForwardButtonUpcomingMovie() {
        return forwardButtonUpcomingMovie;
    }

    public void setForwardButtonUpcomingMovie(Button forwardButtonUpcomingMovie) {
        this.forwardButtonUpcomingMovie = forwardButtonUpcomingMovie;
    }

    public Button getBackButtonMovieUpcoming() {
        return backButtonMovieUpcoming;
    }

    public void setBackButtonMovieUpcoming(Button backButtonMovieUpcoming) {
        this.backButtonMovieUpcoming = backButtonMovieUpcoming;
    }

    public Button getBackButtonUpcomingTv() {
        return backButtonUpcomingTv;
    }

    public void setBackButtonUpcomingTv(Button backButtonUpcomingTv) {
        this.backButtonUpcomingTv = backButtonUpcomingTv;
    }

    public Button getForwardButtonUpcomingTv() {
        return forwardButtonUpcomingTv;
    }

    public void setForwardButtonUpcomingTv(Button forwardButtonUpcomingTv) {
        this.forwardButtonUpcomingTv = forwardButtonUpcomingTv;
    }
}