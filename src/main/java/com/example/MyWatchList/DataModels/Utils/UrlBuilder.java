package com.example.MyWatchList.DataModels.Utils;

import javafx.scene.image.Image;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Locale;

//짜장 불닭 볶음면
public class UrlBuilder {
    private UrlBuilder(){}
    private static final String ISO = String.valueOf(Locale.getDefault());
    private static final String BASE_POSTER_w300 = "https://image.tmdb.org/t/p/w300";
    private static final String BASE_POSTER_w92 = "https://image.tmdb.org/t/p/w92";
    private static final String BASE_BACKDROP_URL = "https://image.tmdb.org/t/p/w1280";
    private static final String BASE_LOGO_URL = "https://image.tmdb.org/t/p/w154";
    private static final String BASE_PROFILE_URL = "https://image.tmdb.org/t/p/w185";
    private static final String BASE_TMDB_WATCH_PAGE = "https://www.themoviedb.org/movie/%d/watch";
    private static final String BASE_YOUTUBE_THUMBNAIL = "https://img.youtube.com/vi/%s/0.jpg";
    private static final String BASE_YOUTUBE_WATCH_LINK = "https://www.youtube.com/watch?v=%s";
    private static final String TMDB_MOVIE_PAGE = "https://api.themoviedb.org/3/movie/";
    private static final String TMDB_ACTOR_ACTRESS_PAGE = "https://api.themoviedb.org/3/person/";

    @NotNull
    @Contract(pure = true)
    public static String getBasePosterw300(String imgKey){return BASE_POSTER_w300 + imgKey;}

    @NotNull
    @Contract(pure = true)
    public static String getBackDropImageURL(String imgKey){
        return BASE_BACKDROP_URL + imgKey;
    }

    @NotNull
    @Contract(pure = true)
    public static String getLogoImageURL(String imgKey){
        return BASE_LOGO_URL + imgKey;
    }

    @NotNull
    @Contract(pure = true)
    public static String getProfileImageURL(String imgKey){return BASE_PROFILE_URL + imgKey;}

    public static String getWatchLink (int pageID){return String.format(BASE_TMDB_WATCH_PAGE,pageID);}

    @NotNull
    @Contract(pure = true)
    public static String getYoutubeThumbnail(String ytKey){return String.format(BASE_YOUTUBE_THUMBNAIL, ytKey);}

    @NotNull
    @Contract(pure = true)
    public static String getBaseYoutubeWatchLink(String ytKey){return String.format(BASE_YOUTUBE_WATCH_LINK, ytKey);}

    @NotNull
    @Contract(pure = true)
    public static String getTmdbMoviePage(int Id){return TMDB_MOVIE_PAGE + Id + "?append_to_response=credits%2Crecommendations%2Creviews%2Cvideos&language=" + ISO;}

    @NotNull
    @Contract(pure = true)
    public static String getTmdbActorActressPage(int Id){return TMDB_ACTOR_ACTRESS_PAGE + Id + "?append_to_response=combined_credits&language=" + ISO;}

    @NotNull
    @Contract(pure = true)
    public static String getBasePosterw92(String imgKey){return BASE_POSTER_w92 + imgKey;}



}
