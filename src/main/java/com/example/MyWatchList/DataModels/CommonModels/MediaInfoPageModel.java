package com.example.MyWatchList.DataModels.CommonModels;

public abstract class MediaInfoPageModel {
    protected String backdrop_path;
    protected GenresModel[] genres;
    protected String homepage;
    protected int id;
    protected String poster_path;
    protected ProductionCompaniesModel[] production_companies;
    protected ProductionCountriesModel[] production_countries;
    protected String overview;
    protected String status;
    protected String tagline;
    protected CreditsModel credits;
    protected RecommendationsModel recommendations;
    protected ReviewsModel reviews;
    protected VideosModel videos;
    protected double vote_average;
    protected int vote_count;

    public String getBackdrop_path() {return backdrop_path;}
    public GenresModel[] getGenres() {return genres;}
    public String getHomepage() {return homepage;}
    public int getId() {return id;}
    public int getVote_count() {return vote_count;}
    public String getPoster_path() {return poster_path;}
    public ProductionCompaniesModel[] getProduction_companies() {return production_companies;}
    public ProductionCountriesModel[] getProduction_countries() {return production_countries;}
    public String getOverview() {return overview;}
    public String getStatus() {return status;}
    public String getTagline() {return tagline;}
    public CreditsModel getCredits() {return credits;}
    public RecommendationsModel getRecommendations() {return recommendations;}
    public ReviewsModel getReviews() {return reviews;}
    public VideosModel getVideos() {return videos;}
    public double getVote_average() {return vote_average;}
}
