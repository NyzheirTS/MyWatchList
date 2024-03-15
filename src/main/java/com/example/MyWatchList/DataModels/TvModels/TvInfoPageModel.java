package com.example.MyWatchList.DataModels.TvModels;

import com.example.MyWatchList.DataModels.*;
import com.example.MyWatchList.DataModels.MovieModels.MovieInfoPageModel;
import com.google.gson.Gson;

import javax.xml.namespace.QName;

public class TvInfoPageModel {
    private String backdrop_path;
    private CreatedBy[] created_by;
    private String first_air_date;
    private Genres[] genres;
    private String homepage;
    private int id;
    private boolean in_production;
    private String last_air_date;
    private EpisodeToAir last_episode_to_air;
    private String name;
    private EpisodeToAir next_episode_to_air;
    private Networks[] networks;
    private int number_of_episodes;
    private int number_of_seasons;
    private String overview;
    private ProductionCompanies[] production_companies;
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
    public void setBackdrop_path(String backdrop_path) {this.backdrop_path = backdrop_path;}
    public CreatedBy[] getCreated_by() {return created_by;}
    public void setCreated_by(CreatedBy[] created_by) {this.created_by = created_by;}
    public String getFirst_air_date() {return first_air_date;}
    public void setFirst_air_date(String first_air_date) {this.first_air_date = first_air_date;}
    public Genres[] getGenres() {return genres;}
    public void setGenres(Genres[] genres) {this.genres = genres;}
    public String getHomepage() {return homepage;}
    public void setHomepage(String homepage) {this.homepage = homepage;}
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public boolean isIn_production() {return in_production;}
    public void setIn_production(boolean in_production) {this.in_production = in_production;}
    public String getLast_air_date() {return last_air_date;}
    public void setLast_air_date(String last_air_date) {this.last_air_date = last_air_date;}
    public EpisodeToAir getLast_episode_to_air() {return last_episode_to_air;}
    public void setLast_episode_to_air(EpisodeToAir last_episode_to_air) {this.last_episode_to_air = last_episode_to_air;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public EpisodeToAir getNext_episode_to_air() {return next_episode_to_air;}
    public void setNext_episode_to_air(EpisodeToAir next_episode_to_air) {this.next_episode_to_air = next_episode_to_air;}
    public Networks[] getNetworks() {return networks;}
    public void setNetworks(Networks[] networks) {this.networks = networks;}
    public int getNumber_of_episodes() {return number_of_episodes;}
    public void setNumber_of_episodes(int number_of_episodes) {this.number_of_episodes = number_of_episodes;}
    public int getNumber_of_seasons() {return number_of_seasons;}
    public void setNumber_of_seasons(int number_of_seasons) {this.number_of_seasons = number_of_seasons;}
    public String getOverview() {return overview;}
    public void setOverview(String overview) {this.overview = overview;}
    public ProductionCompanies[] getProduction_companies() {return production_companies;}
    public void setProduction_companies(ProductionCompanies[] production_companies) {this.production_companies = production_companies;}
    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}
    public String getTagline() {return tagline;}
    public void setTagline(String tagline) {this.tagline = tagline;}
    public String getType() {return type;}
    public void setType(String type) {this.type = type;}
    public double getVote_average() {return vote_average;}
    public void setVote_average(double vote_average) {this.vote_average = vote_average;}
    public CreditsModel getCredits() {return credits;}
    public void setCredits(CreditsModel credits) {this.credits = credits;}
    public RecommendationsModel getRecommendations() {return recommendations;}
    public void setRecommendations(RecommendationsModel recommendations) {this.recommendations = recommendations;}
    public ReviewsModel getReviews() {return reviews;}
    public void setReviews(ReviewsModel reviews) {this.reviews = reviews;}
    public VideosModel getVideos() {return videos;}
    public void setVideos(VideosModel videos) {this.videos = videos;}
    public ContentRatingsModel getContent_ratings() {return content_ratings;}
    public void setContent_ratings(ContentRatingsModel content_ratings) {this.content_ratings = content_ratings;}


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
        public void setId(int id) {this.id = id;}
        public String getName() {return name;}
        public void setName(String name) {this.name = name;}
        public int getGender() {return gender;}
        public void setGender(int gender) {this.gender = gender;}
        public String getProfile_path() {return profile_path;}
        public void setProfile_path(String profile_path) {this.profile_path = profile_path;}
    }

    public static class Genres{
        private int id;
        private String name;
        public int getId() {return id;}
        public void setId(int id) {this.id = id;}
        public String getName() {return name;}
        public void setName(String name) {this.name = name;}
    }

    public static class EpisodeToAir {
        private int id;
        private String name;
        private String overview;
        private double vote_average;
        private String air_date;
        private int episode_number;
        private int runtime;
        private int season_number;
        private int show_id;
        private String still_path;


        public int getId() {return id;}
        public void setId(int id) {this.id = id;}
        public String getName() {return name;}
        public void setName(String name) {this.name = name;}
        public String getOverview() {return overview;}
        public void setOverview(String overview) {this.overview = overview;}
        public double getVote_average() {return vote_average;}
        public void setVote_average(double vote_average) {this.vote_average = vote_average;}
        public String getAir_date() {return air_date;}
        public void setAir_date(String air_date) {this.air_date = air_date;}
        public int getEpisode_number() {return episode_number;}
        public void setEpisode_number(int episode_number) {this.episode_number = episode_number;}
        public int getRuntime() {return runtime;}
        public void setRuntime(int runtime) {this.runtime = runtime;}
        public int getSeason_number() {return season_number;}
        public void setSeason_number(int season_number) {this.season_number = season_number;}
        public int getShow_id() {return show_id;}
        public void setShow_id(int show_id) {this.show_id = show_id;}
        public String getStill_path() {return still_path;}
        public void setStill_path(String still_path) {this.still_path = still_path;}
    }

    public static class Networks{
        private int id;
        private String logo_path;
        private String name;
        private String origin_country;

        public int getId() {return id;}
        public void setId(int id) {this.id = id;}
        public String getLogo_path() {return logo_path;}
        public void setLogo_path(String logo_path) {this.logo_path = logo_path;}
        public String getName() {return name;}
        public void setName(String name) {this.name = name;}
        public String getOrigin_country() {return origin_country;}
        public void setOrigin_country(String origin_country) {this.origin_country = origin_country;}
    }

    public static class ProductionCompanies{
        private int id;
        private String logo_path;
        private String name;
        private String origin_country;

        public int getId() {return id;}
        public void setId(int id) {this.id = id;}
        public String getLogo_path() {return logo_path;}
        public void setLogo_path(String logo_path) {this.logo_path = logo_path;}
        public String getName() {return name;}
        public void setName(String name) {this.name = name;}
        public String getOrigin_country() {return origin_country;}
        public void setOrigin_country(String origin_country) {this.origin_country = origin_country;}
    }
}
