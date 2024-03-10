package com.example.MyWatchList.HomePage;

import com.example.MyWatchList.ApiClass.ApiConnection;
import com.example.MyWatchList.DataClasses.*;
import javafx.application.Platform;
import org.jetbrains.annotations.NotNull;

public class HomePageController {
    private final TrendingCarouselModel trending;
    private final TopRatedCarouselModel toprated;
    private final UpcomingCarouselModel upcoming;

        //TODO: work on implements a way to assign a medatype variable to objects for later tmdb api use on the info page
        //TODO: work on creating a info page so when you click on a object it brings a new page with all info associated to the object clicked

    public HomePageController(TrendingCarouselModel trending, TopRatedCarouselModel toprated, UpcomingCarouselModel upcoming) {
        this.trending = trending;
        this.toprated = toprated;
        this.upcoming = upcoming;
        setTrendingCarousel(true);
    }

    private boolean trendingLoaded = false;
    public void setTrendingCarousel(boolean state){
        if (!trendingLoaded){
            Platform.runLater(() -> {
                movieTrendingWeekArray();
                tvTrendingArray();
                trendingLoaded = state;
            });
        }
    }

    private boolean upcomingLoaded = false;
    public void setUpcomingCarousel(boolean state){
        if(!upcomingLoaded) {
            Platform.runLater(() -> {
                movieUpcomingArray();
                tvUpcomingArray();
                upcomingLoaded = state;
            });
        }
    }

    private boolean topratedLoaded = false;
    public void setTopratedCarousel(boolean state){
        if (!topratedLoaded) {
            Platform.runLater(() -> {
                movieTopRatedArray();
                tvTopRatedArray();
                topratedLoaded = state;
            });
        }
    }


    private void movieTrendingWeekArray(){
            String jsonResponse = ApiConnection.getResponseData(ApiConnection.ApiCallType.MOVIE_TRENDING_WEEK);
            TMDBMovieData[] movies = TMDBMovieData.fromJson(jsonResponse);
            final Carousel movieTrendingCarousel = getCarouselMovie(new Carousel(trending.getTrendingMovieHbox(), trending.getForwardButtonMovieTrending(), trending.getBackButtonMovieTrending()), movies);
            trending.getForwardButtonMovieTrending().setOnAction(event -> movieTrendingCarousel.navigate(1));
            trending.getBackButtonMovieTrending().setOnAction(event -> movieTrendingCarousel.navigate(-1));
    }

    private void movieUpcomingArray(){
            String jsonResponse = ApiConnection.getResponseData(ApiConnection.ApiCallType.MOVIE_UPCOMING);
            TMDBMovieData[] movieUpcoming = TMDBMovieData.fromJson(jsonResponse);
            final Carousel movieUcomingCarousel = getCarouselMovie(new Carousel(upcoming.getUpcomingMovieHbox(),upcoming.getForwardButtonUpcomingMovie(), upcoming.getBackButtonMovieUpcoming()), movieUpcoming);
            upcoming.getForwardButtonUpcomingMovie().setOnAction(event -> movieUcomingCarousel.navigate(1));
            upcoming.getBackButtonMovieUpcoming().setOnAction(event -> movieUcomingCarousel.navigate(-1));
    }

    private void movieTopRatedArray(){
            String jsonResponse = ApiConnection.getResponseData(ApiConnection.ApiCallType.MOVIE_TOPRATED);
            TMDBMovieData[] movieTopRated = TMDBMovieData.fromJson(jsonResponse);
            final Carousel movieTopRatedCarousel = getCarouselMovie(new Carousel(toprated.getTopRatedMovieHbox(),toprated.getForwardButtonTopRatedMovie(),toprated.getBackButtonTopRatedMovies()), movieTopRated);
            toprated.getForwardButtonTopRatedMovie().setOnAction(event -> movieTopRatedCarousel.navigate(1));
            toprated.getBackButtonTopRatedMovies().setOnAction(event -> movieTopRatedCarousel.navigate(-1));
    }

    private  void tvTrendingArray(){
            String jsonResponse = ApiConnection.getResponseData(ApiConnection.ApiCallType.TV_TRENDING_WEEK);
            TMDBTvData[] tvs = TMDBTvData.fromJson(jsonResponse);
            final Carousel tvCarousel = getCarouselTV(new Carousel(trending.getTrendingTvHbox(), trending.getForwardButtonTvTrending(), trending.getBackButtonTVTrending()), tvs);
            trending.getForwardButtonTvTrending().setOnAction(event -> tvCarousel.navigate(1));
            trending.getBackButtonTVTrending().setOnAction(event -> tvCarousel.navigate(-1));
    }

    private void tvUpcomingArray(){
        String jsonResponse = ApiConnection.getResponseData(ApiConnection.ApiCallType.TV_UPCOMING);
        TMDBTvData[] tvUpcoming = TMDBTvData.fromJson(jsonResponse);
        final Carousel tvUpcomingCarousel = getCarouselTV(new Carousel(upcoming.getUpcomingTvHbox(),upcoming.getForwardButtonUpcomingTv(), upcoming.getBackButtonUpcomingTv()), tvUpcoming);
        upcoming.getForwardButtonUpcomingTv().setOnAction(event -> tvUpcomingCarousel.navigate(1));
        upcoming.getBackButtonUpcomingTv().setOnAction(event -> tvUpcomingCarousel.navigate(-1));
    }

    private void tvTopRatedArray(){
        String jsonResponse = ApiConnection.getResponseData(ApiConnection.ApiCallType.TV_TOPRATED);
        TMDBTvData[] tvTopRated = TMDBTvData.fromJson(jsonResponse);
        final Carousel tvTopRatedCarousel = getCarouselTV(new Carousel(toprated.getTopRatedTvHbox(),toprated.getForwardButtonTopRatedTv(),toprated.getBackButtonTopRatedTv()), tvTopRated);
        toprated.getForwardButtonTopRatedTv().setOnAction(event -> tvTopRatedCarousel.navigate(1));
        toprated.getBackButtonTopRatedTv().setOnAction(event -> tvTopRatedCarousel.navigate(-1));
    }


    @NotNull
    private Carousel getCarouselMovie(Carousel carousel, TMDBMovieData[] movies) {
        for (TMDBMovieData movie : movies) {
            PosterNode node = new PosterNode(
                    movie.getPosterPath(),
                    movie.getId(),
                    movie.getVote_average(),
                    movie.getVote_count()
            );
            carousel.addItem(node);
        }
        carousel.updateDisplay();
        return carousel;
    }

    @NotNull
    private Carousel getCarouselTV(Carousel carousel, TMDBTvData[] tvs) {
        for (TMDBTvData tv : tvs) {
            PosterNode node = new PosterNode(
                    tv.getPoster_path(),
                    tv.getId(),
                    tv.getVote_average(),
                    tv.getVote_count()
            );
            carousel.addItem(node);
        }
        carousel.updateDisplay();
        return carousel;
    }
}
