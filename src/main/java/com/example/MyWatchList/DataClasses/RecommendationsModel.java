package com.example.MyWatchList.DataClasses;

public class RecommendationsModel {
    private Recommendations[] results;

    public Recommendations[] getResults() {return results;}
    public void setResults(Recommendations[] results) {this.results = results;}

    public static class Recommendations {
        private int id;
        private String title;
        private String poster_path;
        private String media_type;
        private double vote_average;

        public int getId() {return id;}
        public void setId(int id) {this.id = id;}
        public String getTitle() {return title;}
        public void setTitle(String title) {this.title = title;}
        public String getPoster_path() {return poster_path;}
        public void setPoster_path(String poster_path) {this.poster_path = poster_path;}
        public String getMedia_type() {return media_type;}
        public void setMedia_type(String media_type) {this.media_type = media_type;}
        public double getVote_average() {return vote_average;}
        public void setVote_average(double vote_average) {this.vote_average = vote_average;}
    }
}
