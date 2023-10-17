package com.example.test_uijfx;
import com.google.gson.Gson;

public class MovieDetails {

    int id;
    String poster_path;
    String backdrop_path;
    String overview;


    //Getters
    public String getPosterPath(){return this.poster_path;}
    public void setPoster_path(String poster_path) {this.poster_path = poster_path;}

    public int getId(){return this.id;}
    public void setId(int id) {this.id = id;}

    public String getBackdrop_path() {return backdrop_path;}
    public void setBackdrop_path(String backdrop_path) {this.backdrop_path = backdrop_path;}

    public String getOverview() {return overview;}

    public void setOverview(String overview) {this.overview = overview;}

    public static MovieDetails[] fromJson(String json) {
        Gson gson = new Gson();
        moiveResponse movieList = gson.fromJson(json, moiveResponse.class);
        return movieList.results;
    }

}

