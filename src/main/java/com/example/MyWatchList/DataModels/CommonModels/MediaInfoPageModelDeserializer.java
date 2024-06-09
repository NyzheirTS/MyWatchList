package com.example.MyWatchList.DataModels.CommonModels;

import com.google.gson.Gson;

public class MediaInfoPageModelDeserializer {
    private MediaInfoPageModelDeserializer(){}
    private static final Gson gson = new Gson();

    public static <T extends MediaInfoPageModel> T fromJson(String json, Class<T> classOfType){
        return gson.fromJson(json, classOfType);
    }
}
