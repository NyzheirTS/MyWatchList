package com.example.MyWatchList.DataClasses;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TMDBTvData {
    int id;
    String poster_path;
    String backdrop_path;
    String overview;
    String name;
    Double vote_average;
    int vote_count;

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getPoster_path() {return poster_path;}
    public void setPoster_path(String poster_path) {this.poster_path = poster_path;}

    public String getBackdrop_path() {return backdrop_path;}
    public void setBackdrop_path(String backdrop_path) {this.backdrop_path = backdrop_path;}

    public String getOverview() {return overview;}
    public void setOverview(String overview) {this.overview = overview;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public Double getVote_average() {return vote_average;}
    public void setVote_average(Double vote_average) {this.vote_average = vote_average;}

    public int getVote_count() {return vote_count;}
    public void setVote_count(int vote_count) {this.vote_count = vote_count;}



    public static TMDBTvData[] fromJson(String json) {
        Gson gson = new Gson();
        JsonResponse<TMDBTvData> tvList = gson.fromJson(json, new TypeToken<JsonResponse<TMDBTvData>>() {}.getType());
        return tvList.getResults();
    }


}
