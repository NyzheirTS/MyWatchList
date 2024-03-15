package com.example.MyWatchList.DataModels;

public class ReviewsModel {
    private Reviews[] results;

    public Reviews[] getResults() {return results;}
    public void setResults(Reviews[] results) {this.results = results;}

    public static class Reviews {
        private String author;
        private AuthorDetails author_details;
        private String content;
        private String url;

        public String getAuthor() {return author;}
        public void setAuthor(String author) {this.author = author;}
        public AuthorDetails getAuthor_details() {return author_details;}
        public void setAuthor_details(AuthorDetails author_details) {this.author_details = author_details;}
        public String getContent() {return content;}
        public void setContent(String content) {this.content = content;}
        public String getUrl() {return url;}
        public void setUrl(String url) {this.url = url;}


    }
    public static class AuthorDetails {
        private String username;
        private String avatar_path;
        private int rating;

        public String getUsername() {return username;}
        public void setUsername(String username) {this.username = username;}
        public String getAvatar_path() {return avatar_path;}
        public void setAvatar_path(String avatar_path) {this.avatar_path = avatar_path;}
        public int getRating() {return rating;}
        public void setRating(int rating) {this.rating = rating;}
    }
}
