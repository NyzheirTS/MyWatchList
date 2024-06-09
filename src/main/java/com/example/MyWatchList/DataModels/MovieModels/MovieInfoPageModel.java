package com.example.MyWatchList.DataModels.MovieModels;

import com.example.MyWatchList.DataModels.CommonModels.MediaInfoPageModel;

public class MovieInfoPageModel extends MediaInfoPageModel {
    private BelongsToCollection belongs_to_collection;
    private int budget;
    private String release_date;
    private long revenue;
    private int runtime;
    private String title;

    public BelongsToCollection getBelongs_to_collection() {return belongs_to_collection;}
    public int getBudget() {return budget;}
    public String getRelease_date() {return release_date;}
    public long getRevenue() {return revenue;}
    public int getRuntime() {return runtime;}
    public String getTitle() {return title;}


    public static class BelongsToCollection{
        int id;
        String name;
        String poster_path;
        String backdrop_path;

        public int getId() {return id;}
        public String getName() {return name;}
        public String getPoster_path() {return poster_path;}
        public String getBackdrop_path() {return backdrop_path;}
    }
}
