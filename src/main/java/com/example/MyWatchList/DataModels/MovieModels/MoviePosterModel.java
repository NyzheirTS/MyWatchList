package com.example.MyWatchList.DataModels.MovieModels;
import com.example.MyWatchList.DataModels.JsonResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MoviePosterModel {

    private int id;
    private String poster_path;
    private String title;
    private Double vote_average;
    private String media_type = "movie";


    //Standard Getters and Setters

    public String getPosterPath(){return poster_path;}
    public int getId(){return id;}
    public String getTitle() {return title;}
    public Double getVote_average() {return vote_average;}
    public String getMedia_type() {return media_type;}



//Deserialize the json string ..

    public static MoviePosterModel[] fromJson(String json) {
        Gson gson = new Gson();
        JsonResponse<MoviePosterModel> movieList = gson.fromJson(json, new TypeToken<JsonResponse<MoviePosterModel>>() {}.getType());
        return movieList.getResults();
    }
}

