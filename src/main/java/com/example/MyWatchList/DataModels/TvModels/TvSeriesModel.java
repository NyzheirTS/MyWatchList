package com.example.MyWatchList.DataModels.TvModels;

import com.example.MyWatchList.DataModels.CommonModels.MediaInfoPageModel;


public class TvSeriesModel extends MediaInfoPageModel {
    private CreatedBy[] created_by;
    private int[] episode_run_time;
    private String first_air_date;
    private EpisodeAir next_episode_to_air;
    private EpisodeAir last_episode_to_air;
    private Seasons[] seasons;
    private boolean in_production;
    private String last_air_date;
    private String name;
    private Networks[] networks;
    private int number_of_episodes;
    private int number_of_seasons;
    private String type;

    public CreatedBy[] getCreated_by() {return created_by;}
    public int[] getEpisode_run_time() {return episode_run_time;}
    public String getFirst_air_date() {return first_air_date;}
    public EpisodeAir getNext_episode_to_air() {return next_episode_to_air;}
    public EpisodeAir getLast_episode_to_air() {return last_episode_to_air;}
    public Seasons[] getSeasons() {return seasons;}
    public boolean isIn_production() {return in_production;}
    public String getLast_air_date() {return last_air_date;}
    public String getName() {return name;}
    public Networks[] getNetworks() {return networks;}
    public int getNumber_of_episodes() {return number_of_episodes;}
    public int getNumber_of_seasons() {return number_of_seasons;}
    public String getType() {return type;}


    public static class CreatedBy {
        private int id;
        private String name;
        private int gender;
        private String profile_path;

        public int getId() {return id;}
        public String getName() {return name;}
        public int getGender() {return gender;}
        public String getProfile_path() {return profile_path;}
    }

    public static class EpisodeAir {
        private int id;
        private String name;
        private String air_date;
        private int episode_number;
        private String episode_type;
        private int runtime;
        private int season_number;
        private String still_path;
        private int show_id;

        public int getId() {return id;}
        public String getName() {return name;}
        public String getAir_date() {return air_date;}
        public int getEpisode_number() {return episode_number;}
        public String getEpisode_type() {return episode_type;}
        public int getRuntime() {return runtime;}
        public int getSeason_number() {return season_number;}
        public String getStill_path() {return still_path;}
        public int getShow_id() {return show_id;}
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
