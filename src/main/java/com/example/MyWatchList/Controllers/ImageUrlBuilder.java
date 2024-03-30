package com.example.MyWatchList.Controllers;
//불닭
public class ImageUrlBuilder {
    private static final String BASE_POSTER_URL = "https://image.tmdb.org/t/p/w300";
    private static final String BASE_BACKDROP_URL = "https://image.tmdb.org/t/p/w1280";
    private static final String BASE_LOGO_URL = "https://image.tmdb.org/t/p/w154";
    private static final String BASE_PROFILE_URL = "https://image.tmdb.org/t/p/w185";

    public static String getPosterImageURL(String imgKey){
        return BASE_POSTER_URL + imgKey;
    }
    public static String getBackDropImageURL(String imgKey){
        return BASE_BACKDROP_URL + imgKey;
    }
    public static String getLogoImageURL(String imgKey){
        return BASE_LOGO_URL + imgKey;
    }
    public static String getProfileImageURL(String imgKey){
        return BASE_PROFILE_URL + imgKey;
    }
}
