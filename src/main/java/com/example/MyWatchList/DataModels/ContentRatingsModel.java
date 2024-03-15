package com.example.MyWatchList.DataModels;

public class ContentRatingsModel {
    private ContentRatings[] results;

    public ContentRatings[] getResults() {return results;}
    public void setResults(ContentRatings[] results) {this.results = results;}

    public static class ContentRatings {
        private String iso_3166_1;
        private String rating;

        public String getIso_3166_1() {return iso_3166_1;}
        public void setIso_3166_1(String iso_3166_1) {this.iso_3166_1 = iso_3166_1;}
        public String getRating() {return rating;}
        public void setRating(String rating) {this.rating = rating;}
    }
}
