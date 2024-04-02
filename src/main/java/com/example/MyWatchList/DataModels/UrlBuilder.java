package com.example.MyWatchList.DataModels;
//불닭
public class UrlBuilder {
    private static final String BASE_POSTER_URL = "https://image.tmdb.org/t/p/w300";
    private static final String BASE_BACKDROP_URL = "https://image.tmdb.org/t/p/w1280";
    private static final String BASE_LOGO_URL = "https://image.tmdb.org/t/p/w154";
    private static final String BASE_PROFILE_URL = "https://image.tmdb.org/t/p/w185";
    private static final String BASE_TMDB_WATCH_PAGE = "https://www.themoviedb.org/movie/%d/watch";
    private static final String BASE_YOUTUBE_EMBED_LINK = "https://www.youtube.com/embed/";

    public static String getPosterImageURL(String imgKey){return BASE_POSTER_URL + imgKey;}
    public static String getBackDropImageURL(String imgKey){
        return BASE_BACKDROP_URL + imgKey;
    }
    public static String getLogoImageURL(String imgKey){
        return BASE_LOGO_URL + imgKey;
    }
    public static String getProfileImageURL(String imgKey){return BASE_PROFILE_URL + imgKey;}
    public static String getWatchLink (int pageID){return String.format(BASE_TMDB_WATCH_PAGE,pageID);}
    public static String getYoutubeLink(String ytKey){return BASE_YOUTUBE_EMBED_LINK + ytKey;}
}
