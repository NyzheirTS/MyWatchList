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
    public void setBackdrop_path(String backdrop_path) {this.backdrop_path = backdrop_path;}
    public BelongsToCollection getBelongs_to_collections() {return belongs_to_collection;}
    public void setBelongs_to_collections(BelongsToCollection belongs_to_collections) {this.belongs_to_collection = belongs_to_collections;}
    public int getBudget() {return budget;}
    public void setBudget(int budget) {this.budget = budget;}
    public Genres[] getGenres() {return genres;}
    public void setGenres(Genres[] genres) {this.genres = genres;}
    public String getHomepage() {return homepage;}
    public void setHomepage(String homepage) {this.homepage = homepage;}
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getOverview() {return overview;}
    public void setOverview(String overview) {this.overview = overview;}
    public String getPoster_path() {return poster_path;}
    public void setPoster_path(String poster_path) {this.poster_path = poster_path;}
    public String getRelease_date() {return release_date;}
    public void setRelease_date(String release_date) {this.release_date = release_date;}
    public int getRevenue() {return revenue;}
    public void setRevenue(int revenue) {this.revenue = revenue;}
    public int getRuntime() {return runtime;}
    public void setRuntime(int runtime) {this.runtime = runtime;}
    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}
    public String getTagline() {return tagline;}
    public void setTagline(String tagline) {this.tagline = tagline;}
    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}
    public Double getVote_average() {return vote_average;}
    public void setVote_average(Double vote_average) {this.vote_average = vote_average;}
    public int getVote_count() {return vote_count;}
    public void setVote_count(int vote_count) {this.vote_count = vote_count;}
    public CreditsModel getCredits() {return credits;}
    public void setCredits(CreditsModel credits) {this.credits = credits;}
    public RecommendationsModel getRecommendations() {return recommendations;}
    public void setRecommendations(RecommendationsModel recommendations) {this.recommendations = recommendations;}
    public ReviewsModel getReviews() {return reviews;}
    public void setReviews(ReviewsModel reviews) {this.reviews = reviews;}
    public VideosModel getVideos() {return videos;}
    public void setVideos(VideosModel videos) {this.videos = videos;}
    public ProductionCompanies[] getProduction_companies() {return production_companies;}
    public void setProduction_companies(ProductionCompanies[] production_companies) {this.production_companies = production_companies;}

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
        public void setId(int id) {this.id = id;}
        public String getName() {return name;}
        public void setName(String name) {this.name = name;}
        public String getPoster_path() {return poster_path;}
        public void setPoster_path(String poster_path) {this.poster_path = poster_path;}
        public String getBackdrop_path() {return backdrop_path;}
        public void setBackdrop_path(String backdrop_path) {this.backdrop_path = backdrop_path;}
    }
}
