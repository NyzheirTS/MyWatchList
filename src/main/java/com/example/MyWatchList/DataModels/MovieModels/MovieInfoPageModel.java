package com.example.MyWatchList.DataModels.MovieModels;

import com.example.MyWatchList.DataModels.*;
import com.google.gson.Gson;

public class MovieInfoPageModel {
    private String backdrop_path;
    private BelongsToCollection belongs_to_collection;
    private int budget;
    private Genres[] genres;
    private String homepage;
    private int id;
    private String overview;
    private String poster_path;
    private ProductionCompanies[] production_companies;
    private String release_date;
    private int revenue;
    private int runtime;
    private String status;
    private String tagline;
    private String title;
    private Double vote_average;
    private int vote_count;
    private CreditsModel credits;
    private RecommendationsModel recommendations;
    private ReviewsModel reviews;
    private VideosModel videos;

    public String getBackdrop_path() {return backdrop_path;}
    public BelongsToCollection getBelongs_to_collection() {return belongs_to_collection;}
    public int getBudget() {return budget;}
    public Genres[] getGenres() {return genres;}
    public String getHomepage() {return homepage;}
    public int getId() {return id;}
    public String getOverview() {return overview;}
    public String getPoster_path() {return poster_path;}
    public ProductionCompanies[] getProduction_companies() {return production_companies;}
    public String getRelease_date() {return release_date;}
    public int getRevenue() {return revenue;}
    public int getRuntime() {return runtime;}
    public String getStatus() {return status;}
    public String getTagline() {return tagline;}
    public String getTitle() {return title;}
    public Double getVote_average() {return vote_average;}
    public int getVote_count() {return vote_count;}
    public CreditsModel getCredits() {return credits;}
    public RecommendationsModel getRecommendations() {return recommendations;}
    public ReviewsModel getReviews() {return reviews;}
    public VideosModel getVideos() {return videos;}

    public static MovieInfoPageModel fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, MovieInfoPageModel.class);
    }

    public static class BelongsToCollection{
        int id;
        String name;
        String poster_path;
        String backdrop_path;

        public int getId() {return id;}
        public String getName() {return name;}
        public String getPoster_path() {return poster_path;}
        public String getBackdrop_path() {return backdrop_path;}
    }
}
