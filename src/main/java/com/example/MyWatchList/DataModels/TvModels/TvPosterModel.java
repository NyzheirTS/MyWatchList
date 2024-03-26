package com.example.MyWatchList.DataModels.TvModels;

import com.example.MyWatchList.DataModels.JsonResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TvPosterModel {
    private int id;
    private String poster_path;
    private String name;
    private Double vote_average;
    private String media_type = "tv";

    public int getId() {return id;}
    public String getPoster_path() {return poster_path;}
    public String getName() {return name;}
    public Double getVote_average() {return vote_average;}
    public String getMedia_type() {return media_type;}

    public static TvPosterModel[] fromJson(String json) {
        Gson gson = new Gson();
        JsonResponse<TvPosterModel> tvList = gson.fromJson(json, new TypeToken<JsonResponse<TvPosterModel>>() {}.getType());
        return tvList.getResults();
    }


}
