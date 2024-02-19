package com.example.MyWatchList.DataClasses;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TvImageDetails {
    String file_path;

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public static TvImageDetails[] fromJson(String json) {
        Gson gson = new Gson();
        jsonResponse<TvImageDetails> imageList = gson.fromJson(json, new TypeToken<jsonResponse<TvImageDetails>>() {}.getType());
        return imageList.getPosters();
    }

}
