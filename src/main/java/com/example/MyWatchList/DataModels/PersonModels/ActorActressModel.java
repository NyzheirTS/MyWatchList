package com.example.MyWatchList.DataModels.PersonModels;

import com.example.MyWatchList.DataModels.CommonModels.CombinedCredits;
import com.google.gson.Gson;

public class ActorActressModel {

    private static final Gson gson = new Gson();

    private String[] also_known_as;
    private String biography;
    private String birthday;
    private int deathday;
    private String homepage;
    private int id;
    private String known_for_department;
    private String name;
    private String place_of_birth;
    private String profile_path;
    private CombinedCredits combined_credits;

    public String[] getAlso_known_as() {return also_known_as;}
    public String getBiography() {return biography;}
    public String getBirthday() {return birthday;}
    public int getDeathday() {return deathday;}
    public String getHomepage() {return homepage;}
    public int getId() {return id;}
    public String getKnown_for_department() {return known_for_department;}
    public String getName() {return name;}
    public String getPlace_of_birth() {return place_of_birth;}
    public String getProfile_path() {return profile_path;}
    public CombinedCredits getCombined_credits() {return combined_credits;}


    public static ActorActressModel fromJson(String json){
        return gson.fromJson(json, ActorActressModel.class);
    }

}
