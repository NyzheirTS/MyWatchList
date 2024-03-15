package com.example.MyWatchList.DataModels.TvModels;

import com.example.MyWatchList.DataModels.JsonResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TvPosterModel {
    int id;
    private String poster_path;
    private String name;
    private Double vote_average;
    private String media_type = "tv";

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getPoster_path() {return poster_path;}
    public void setPoster_path(String poster_path) {this.poster_path = poster_path;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public Double getVote_average() {return vote_average;}
    public void setVote_average(Double vote_average) {this.vote_average = vote_average;}
    public String getMedia_type() {
        return media_type;
    }
    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }


    public static TvPosterModel[] fromJson(String json) {
        Gson gson = new Gson();
        JsonResponse<TvPosterModel> tvList = gson.fromJson(json, new TypeToken<JsonResponse<TvPosterModel>>() {}.getType());
        return tvList.getResults();
    }


}
