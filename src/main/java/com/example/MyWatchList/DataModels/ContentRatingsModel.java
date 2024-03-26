package com.example.MyWatchList.DataModels;

public class ContentRatingsModel {
    private ContentRatings[] results;

    public ContentRatings[] getResults() {return results;}

    public static class ContentRatings {
        private String iso_3166_1;
        private String rating;

        public String getIso_3166_1() {return iso_3166_1;}
        public String getRating() {return rating;}
    }
}
