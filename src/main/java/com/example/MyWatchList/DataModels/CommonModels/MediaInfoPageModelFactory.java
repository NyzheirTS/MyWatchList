package com.example.MyWatchList.DataModels.CommonModels;

import com.example.MyWatchList.DataModels.MovieModels.MovieInfoPageModel;
import com.example.MyWatchList.DataModels.TvModels.TvSeriesModel;

public class MediaInfoPageModelFactory {
    public static MediaInfoPageModel fromJson(String json, String mediaType) {
        switch (mediaType) {
            case "movie":
                // Deserialize and return MovieInfoPageModel
                return MediaInfoPageModelDeserializer.fromJson(json, MovieInfoPageModel.class);
            case "tv":
                // Deserialize and return TVShowInfoPageModel
                return MediaInfoPageModelDeserializer.fromJson(json, TvSeriesModel.class);
            default:
                throw new IllegalArgumentException("Unsupported media type: " + mediaType);
        }
    }
}
