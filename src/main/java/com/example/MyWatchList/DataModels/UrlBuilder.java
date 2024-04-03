package com.example.MyWatchList.DataModels;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

//짜장 불닭 볶음면
public class UrlBuilder {
    private static final String BASE_POSTER_URL = "https://image.tmdb.org/t/p/w300";
    private static final String BASE_BACKDROP_URL = "https://image.tmdb.org/t/p/w1280";
    private static final String BASE_LOGO_URL = "https://image.tmdb.org/t/p/w154";
    private static final String BASE_PROFILE_URL = "https://image.tmdb.org/t/p/w185";
    private static final String BASE_TMDB_WATCH_PAGE = "https://www.themoviedb.org/movie/%d/watch";
    private static final String BASE_YOUTUBE_EMBED_LINK = "https://www.youtube.com/embed/%s?controls=0&widget_referrer";

    @NotNull
    @Contract(pure = true)
    public static String getPosterImageURL(String imgKey){return BASE_POSTER_URL + imgKey;}
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
    public static String getYoutubeLink(String ytKey){return String.format(BASE_YOUTUBE_EMBED_LINK, ytKey);}
}
