package com.example.MyWatchList.HomePage;

import com.example.MyWatchList.ApiClass.ApiConnection;
import com.example.MyWatchList.DataClasses.*;
import javafx.application.Platform;

public class HomePageController {
    private final TrendingCarouselModel trending;
    private final TopRatedCarouselModel toprated;
    private final UpcomingCarouselModel upcoming;

    public HomePageController(TrendingCarouselModel trending, TopRatedCarouselModel toprated, UpcomingCarouselModel upcoming) {
        this.trending = trending;
        this.toprated = toprated;
        this.upcoming = upcoming;
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
            TMDBMovieData[] movies = TMDBMovieData.fromJson(jsonResponse);
            Carousel movieCarousel = new Carousel(trending.getTrendingMovieHbox());

            for (TMDBMovieData movie : movies) {
                PosterNode node = new PosterNode(
                        movie.getPosterPath(),
                        movie.getId(),
                        movie.getVote_average(),
                        movie.getVote_count()
                );
                movieCarousel.addItem(node.getsNode());
            }
            movieCarousel.updateDisplay();
            trending.getForwardButtonMovieTrending().setOnAction(event -> movieCarousel.navigate(1));
            trending.getBackButtonMovieTrending().setOnAction(event -> movieCarousel.navigate(-1));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private  void tvTrendingWeekArray(){
        try {
            String jsonResponseTV = ApiConnection.getResponseData(ApiConnection.ApiCallType.TV_TRENDING_WEEK);
            TMDBTvData[] tvs = TMDBTvData.fromJson(jsonResponseTV);
            Carousel tvCarousel = new Carousel(trending.getTrendingTvHbox());

            for(TMDBTvData tv: tvs){
                PosterNode node = new PosterNode(
                        tv.getPoster_path(),
                        tv.getId(),
                        tv.getVote_average(),
                        tv.getVote_count()
                );
                tvCarousel.addItem(node.getsNode());
            }
            tvCarousel.updateDisplay();
            trending.getForwardButtonTvTrending().setOnAction(event -> tvCarousel.navigate(1));
            trending.getBackButtonTVTrending().setOnAction(event -> tvCarousel.navigate(-1));
        } catch (Exception e){
            e.printStackTrace();
        }
    }


}
