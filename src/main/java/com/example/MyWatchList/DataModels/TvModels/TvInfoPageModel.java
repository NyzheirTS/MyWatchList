package com.example.MyWatchList.DataModels.TvModels;

import com.example.MyWatchList.DataModels.*;
import com.google.gson.Gson;


public class TvInfoPageModel {
    private String backdrop_path;
    private CreatedBy[] created_by;
    private int[] episode_run_time;
    private String first_air_date;
    private String next_episode_to_air;
    private GenresModel[] genres;
    private String homepage;
    private Seasons[] seasons;
    private int id;
    private boolean in_production;
    private String last_air_date;
    private String name;
    private Networks[] networks;
    private int number_of_episodes;
    private int number_of_seasons;
    private String overview;
    private ProductionCompaniesModel[] production_companies;
    private String status;
    private String tagline;
    private String type;
    private double vote_average;
    private CreditsModel credits;
    private RecommendationsModel recommendations;
    private ReviewsModel reviews;
    private VideosModel videos;
    private ContentRatingsModel content_ratings;


    public String getBackdrop_path() {return backdrop_path;}
    public CreatedBy[] getCreated_by() {return created_by;}
    public int[] getEpisode_run_time() {return episode_run_time;}
    public String getFirst_air_date() {return first_air_date;}
    public String getNext_episode_to_air() {return next_episode_to_air;}
    public Seasons[] getSeasons() {return seasons;}
    public GenresModel[] getGenres() {return genres;}
    public String getHomepage() {return homepage;}
    public int getId() {return id;}
    public boolean isIn_production() {return in_production;}
    public String getLast_air_date() {return last_air_date;}
    public String getName() {return name;}
    public Networks[] getNetworks() {return networks;}
    public int getNumber_of_episodes() {return number_of_episodes;}
    public int getNumber_of_seasons() {return number_of_seasons;}
    public String getOverview() {return overview;}
    public ProductionCompaniesModel[] getProduction_companies() {return production_companies;}
    public String getStatus() {return status;}
    public String getTagline() {return tagline;}
    public String getType() {return type;}
    public double getVote_average() {return vote_average;}
    public CreditsModel getCredits() {return credits;}
    public RecommendationsModel getRecommendations() {return recommendations;}
    public ReviewsModel getReviews() {return reviews;}
    public VideosModel getVideos() {return videos;}
    public ContentRatingsModel getContent_ratings() {return content_ratings;}

    public static TvInfoPageModel fromJson(String Json){
        Gson gson = new Gson();
        return gson.fromJson(Json, TvInfoPageModel.class);
    }

    public static class CreatedBy{
        private int id;
        private String name;
        private int gender;
        private String profile_path;

        public int getId() {return id;}
        public String getName() {return name;}
        public int getGender() {return gender;}
        public String getProfile_path() {return profile_path;}
    }


    public static class Seasons{
        private String air_date;
        private int episode_count;
        private int id;
        private String name;
        private int season_number;

        public String getAir_date() {return air_date;}
        public int getEpisode_count() {return episode_count;}
        public int getId() {return id;}
        public String getName() {return name;}
        public int getSeason_number() {return season_number;}
    }

    public static class Networks{
        private int id;
        private String logo_path;
        private String name;
        private String origin_country;

        public int getId() {return id;}
        public String getLogo_path() {return logo_path;}
        public String getName() {return name;}
        public String getOrigin_country() {return origin_country;}
    }
}
