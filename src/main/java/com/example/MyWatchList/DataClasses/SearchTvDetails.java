package com.example.MyWatchList.DataClasses;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class SearchTvDetails {
    int tv_id;
    String link_to_tmdb;
    String link_to_justwatch;


    public int getTv_id() {
        return tv_id;
    }

    public void setTv_id(int tv_id) {
        this.tv_id = tv_id;
    }

    public String getLink_to_tmdb() {
        return link_to_tmdb;
    }

    public void setLink_to_tmdb(String link_to_tmdb) {
        this.link_to_tmdb = link_to_tmdb;
    }

    public String getLink_to_justwatch() {
        return link_to_justwatch;
    }

    public void setLink_to_justwatch(String link_to_justwatch) {
        this.link_to_justwatch = link_to_justwatch;
    }

    public static SearchTvDetails[] fromJson(String json){
        Gson gson = new Gson();
        jsonResponse<SearchTvDetails> searchList = gson.fromJson(json, new TypeToken<jsonResponse<SearchTvDetails>>() {}.getType());
        return searchList.getResults();
    }

}

