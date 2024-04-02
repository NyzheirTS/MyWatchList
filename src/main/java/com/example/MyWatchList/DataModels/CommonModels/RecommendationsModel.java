package com.example.MyWatchList.DataModels.CommonModels;

public class RecommendationsModel {
    private Recommendations[] results;

    public Recommendations[] getResults() {return results;}

    public static class Recommendations {
        private int id;
        private String title;
        private String name;
        private String poster_path;
        private String media_type;
        private double vote_average;

        public int getId() {return id;}
        public String getTitleMovie() {return title;}
        public String getPoster_path() {return poster_path;}
        public String getMedia_type() {return media_type;}
        public double getVote_average() {return vote_average;}
        public String getNameTV() {return name;}
    }
}
