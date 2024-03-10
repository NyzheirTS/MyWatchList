package com.example.MyWatchList.HomePage;

import com.example.MyWatchList.ApiClass.ApiConnection;
import com.example.MyWatchList.DataClasses.*;
import javafx.application.Platform;

public class HomePageController {
    private final TrendingCarouselModel trending;
    private final TopRatedCarouselModel toprated;
    private final UpcomingCarouselModel upcoming;

    //TODO: Continue to work on imageloading optimization in Carousel and PosterNode
    //TODO: Work on logic for loading and unloading maybe unload images that are 2 pages away from current ie if i am on page 2 of carousel items on page 0 are unloaded
    //TODO: Also look into only loading images based on what tab is currently showing. We do not need to load toprated and upcomming if all we see is the Trending Page.

    public HomePageController(TrendingCarouselModel trending, TopRatedCarouselModel toprated, UpcomingCarouselModel upcoming) {
        this.trending = trending;
        this.toprated = toprated;
        this.upcoming = upcoming;
        initArrays();
    }


    private void initArrays() {
        Platform.runLater(() -> {
            try {
                movieTrendingWeekArray();
                movieUpcomingArray();
                movieTopRatedArray();
                tvTrendingArray();
                tvTopRatedArray();
                tvUpcomingArray();
            } catch (Exception e){
                e.printStackTrace();
            }
        });
    }


    private void movieTrendingWeekArray(){
        try {
            String jsonResponse = ApiConnection.getResponseData(ApiConnection.ApiCallType.MOVIE_TRENDING_WEEK);
            TMDBMovieData[] movies = TMDBMovieData.fromJson(jsonResponse);
            Carousel movieTrendingCarousel = new Carousel(trending.getTrendingMovieHbox());

            for (TMDBMovieData movie : movies) {
                PosterNode node = new PosterNode(
                        movie.getPosterPath(),
                        movie.getId(),
                        movie.getVote_average(),
                        movie.getVote_count()
                );
                movieTrendingCarousel.addItem(node);
            }
            movieTrendingCarousel.updateDisplay();
            trending.getForwardButtonMovieTrending().setOnAction(event -> movieTrendingCarousel.navigate(1));
            trending.getBackButtonMovieTrending().setOnAction(event -> movieTrendingCarousel.navigate(-1));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void movieUpcomingArray(){
        try{
            String jsonResponse = ApiConnection.getResponseData(ApiConnection.ApiCallType.MOVIE_UPCOMING);
            TMDBMovieData[] movieUpcoming = TMDBMovieData.fromJson(jsonResponse);
            Carousel movieUcomingCarousel = new Carousel(upcoming.getUpcomingMovieHbox());

            for(TMDBMovieData movie: movieUpcoming){
                PosterNode node = new PosterNode(
                        movie.getPosterPath(),
                        movie.getId(),
                        movie.getVote_average(),
                        movie.getVote_count()
                );
                movieUcomingCarousel.addItem(node);
            }
            movieUcomingCarousel.updateDisplay();
            upcoming.getForwardButtonUpcomingMovie().setOnAction(event -> movieUcomingCarousel.navigate(1));
            upcoming.getBackButtonMovieUpcoming().setOnAction(event -> movieUcomingCarousel.navigate(-1));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void movieTopRatedArray(){
        try {
            String jsonResponse = ApiConnection.getResponseData(ApiConnection.ApiCallType.MOVIE_TOPRATED);
            TMDBMovieData[] movieTopRated = TMDBMovieData.fromJson(jsonResponse);
            Carousel movieTopRatedCarousel = new Carousel(toprated.getTopRatedMovieHbox());

            for (TMDBMovieData movie : movieTopRated) {
                PosterNode node = new PosterNode(
                        movie.getPosterPath(),
                        movie.getId(),
                        movie.getVote_average(),
                        movie.getVote_count()
                );
                movieTopRatedCarousel.addItem(node);
            }
            movieTopRatedCarousel.updateDisplay();
            toprated.getForwardButtonTopRatedMovie().setOnAction(event -> movieTopRatedCarousel.navigate(1));
            toprated.getBackButtonTopRatedMovies().setOnAction(event -> movieTopRatedCarousel.navigate(-1));
        }catch (Exception e){
            e.printStackTrace();
        }
    }




    private  void tvTrendingArray(){
        try {
            String jsonResponse = ApiConnection.getResponseData(ApiConnection.ApiCallType.TV_TRENDING_WEEK);
            TMDBTvData[] tvs = TMDBTvData.fromJson(jsonResponse);
            Carousel tvCarousel = new Carousel(trending.getTrendingTvHbox());

            for(TMDBTvData tv: tvs){
                PosterNode node = new PosterNode(
                        tv.getPoster_path(),
                        tv.getId(),
                        tv.getVote_average(),
                        tv.getVote_count()
                );
                tvCarousel.addItem(node);
            }
            tvCarousel.updateDisplay();
            trending.getForwardButtonTvTrending().setOnAction(event -> tvCarousel.navigate(1));
            trending.getBackButtonTVTrending().setOnAction(event -> tvCarousel.navigate(-1));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void tvUpcomingArray(){
        String jsonResponse = ApiConnection.getResponseData(ApiConnection.ApiCallType.TV_UPCOMING);
        TMDBTvData[] tvUpcoming = TMDBTvData.fromJson(jsonResponse);
        Carousel tvUpcomingCarousel = new Carousel(upcoming.getUpcomingTvHbox());

        for(TMDBTvData tv : tvUpcoming){
            PosterNode node = new PosterNode(
                    tv.getPoster_path(),
                    tv.getId(),
                    tv.getVote_average(),
                    tv.getVote_count()
            );
            tvUpcomingCarousel.addItem(node);
        }
        tvUpcomingCarousel.updateDisplay();
        upcoming.getForwardButtonUpcomingTv().setOnAction(event -> tvUpcomingCarousel.navigate(1));
        upcoming.getBackButtonUpcomingTv().setOnAction(event -> tvUpcomingCarousel.navigate(-1));
    }

    private void tvTopRatedArray(){
        String jsonResponse = ApiConnection.getResponseData(ApiConnection.ApiCallType.TV_TOPRATED);
        TMDBTvData[] tvTopRated = TMDBTvData.fromJson(jsonResponse);
        Carousel tvTopRatedCarousel = new Carousel(toprated.getTopRatedTvHbox());

        for(TMDBTvData tv : tvTopRated){
            PosterNode node = new PosterNode(
                    tv.getPoster_path(),
                    tv.getId(),
                    tv.getVote_average(),
                    tv.getVote_count()
            );
            tvTopRatedCarousel.addItem(node);
        }
        tvTopRatedCarousel.updateDisplay();
        toprated.getForwardButtonTopRatedTv().setOnAction(event -> tvTopRatedCarousel.navigate(1));
        toprated.getBackButtonTopRatedTv().setOnAction(event -> tvTopRatedCarousel.navigate(-1));
    }

}
