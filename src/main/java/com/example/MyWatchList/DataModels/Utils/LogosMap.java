package com.example.MyWatchList.DataModels.Utils;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class LogosMap {
    private LogosMap(){}

    public static final Map<String, String> URL_MAP = new HashMap<>();
    public static final Map<String, String> IMAGE_MAP = new HashMap<>();


    static {
        URL_MAP.put("facebook_id","https://facebook.com/");
        URL_MAP.put("tiktok_id","https://www.tiktok.com/");
        URL_MAP.put("instagram_id","https://instagram.com/");
        URL_MAP.put("twitter_id","https://x.com/");
        URL_MAP.put("youtube_id","https://www.youtube.com/");

        IMAGE_MAP.put("facebook_id","/com/example/MyWatchList/images/TestIcons/Facebook_Logo_Primary.png");
        IMAGE_MAP.put("tiktok_id","/com/example/MyWatchList/images/TestIcons/TikTok_Icon_Black_Circle.png");
        IMAGE_MAP.put("instagram_id","/com/example/MyWatchList/images/TestIcons/Instagram_Glyph_Gradient.png");
        IMAGE_MAP.put("twitter_id","/com/example/MyWatchList/images/TestIcons/logo-black.png");
        IMAGE_MAP.put("youtube_id","/com/example/MyWatchList/images/TestIcons/youtube_social_icon_red.png");
    }
}
