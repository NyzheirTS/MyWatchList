package com.example.MyWatchList.DataModels.SearchModels;

import com.example.MyWatchList.DataModels.PersonModels.PersonModel;
import com.google.gson.Gson;

public class SearchModel {

    private static final Gson gson = new Gson();
    private Results[] results;
    private int page;
    private int total_pages;

    public int getPage() {return page;}
    public int getTotal_pages() {return total_pages;}
    public Results[] getResults() {return results;}

    public static class Results {
        private int id;
        private String media_type;
        private String name;
        private String title;
        private String poster_path;
        private double vote_average;
        private String first_air_date;
        private String release_date;
        private String overview;
        private String profile_path;
        private String know_for_department;
        private KnownFor[] known_for;


        public int getId() {return id;}
        public String getOverview() {return overview;}
        public KnownFor[] getKnown_for() {return known_for;}
        public String getMedia_type() {
            return media_type;
        }
        public String getName() {
            return name != null ? name : title;
        }
        public String getPoster_path() {
            return poster_path;
        }
        public double getVote_average() {
            return vote_average;
        }
        public String getRelease_date() {
            return release_date != null ? release_date : first_air_date;
        }
        public String getProfile_path() {
            return profile_path;
        }
        public String getKnow_for_department() {
            return know_for_department;
        }


        public static class KnownFor{
            private String name;
            private String title;

            public String getName() {
                return name != null ? name : title;
            }
        }e
    }

    public static SearchModel fromJson(String json){
        return gson.fromJson(json, SearchModel.class);
    }
}
