package com.example.MyWatchList.DataModels.CommonModels;

public class ReviewsModel {
    private Reviews[] results;

    public Reviews[] getResults() {return results;}

    public static class Reviews {
        private String author;
        private AuthorDetails author_details;
        private String content;
        private String created_at;
        private String id;
        private String updated_at;
        private String url;

        public String getAuthor() {return author;}
        public AuthorDetails getAuthor_details() {return author_details;}
        public String getContent() {return content;}
        public String getUrl() {return url;}
        public String getCreated_at() {return created_at;}
        public String getId() {return id;}
        public String getUpdated_at() {return updated_at;}
    }
    public static class AuthorDetails {
        private String username;
        private String avatar_path;
        private double rating;

        public String getUsername() {return username;}
        public String getAvatar_path() {return avatar_path;}
        public double getRating() {return rating;}
    }
}
