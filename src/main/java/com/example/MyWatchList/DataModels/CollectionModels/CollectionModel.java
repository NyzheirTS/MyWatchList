package com.example.MyWatchList.DataModels.CollectionModels;

import com.google.gson.Gson;

public class CollectionModel {
    private static final Gson gson = new Gson();
    int id;
    String overview;
    String backdrop_path;
    String name;
    Parts[] parts;

    public int getId() {return id;}
    public String getOverview() {return overview;}
    public String getBackdrop_path() {return backdrop_path;}
    public String getName() {return name;}
    public Parts[] getParts() {return parts;}

   public static class Parts {
        int id;
        String poster_path;
        String backdrop_path;
        String original_title;
        String media_type;
        String release_date;
        Double vote_average;


        public int getId() {return id;}

        public String getPoster_path() {return poster_path;}

        public String getBackdrop_path() {return backdrop_path;}

        public String getOriginal_title() {return original_title;}

        public String getMedia_type() {return media_type;}

        public String getRelease_date() {return release_date;}

        public Double getVote_average() {return vote_average;}
    }

    public static CollectionModel fromJson(String json){return gson.fromJson(json, CollectionModel.class);}
}
