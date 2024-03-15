package com.example.MyWatchList.ApiClass;
import com.example.MyWatchList.AppConfig.AppConfig;
import com.example.MyWatchList.Caching.JsonCache;
import com.example.MyWatchList.DataModels.ApiCallType;
import javafx.application.Platform;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class ApiConnection {
    private final OkHttpClient client;
    private static final Map<ApiCallType, String> responseData = new EnumMap<>(ApiCallType.class);
    private final Map<ApiCallType, String> endpoints = new EnumMap<>(ApiCallType.class);
    private final JsonCache jsonCache = new JsonCache();

    public ApiConnection() {
        initEndpoints();
        this.client = new OkHttpClient();
    }

    private void initEndpoints(){
        endpoints.put(ApiCallType.TV_UPCOMING,"https://api.themoviedb.org/3/tv/on_the_air?language=en-US&page=1");
        endpoints.put(ApiCallType.TV_TRENDING_WEEK,"https://api.themoviedb.org/3/trending/tv/week?language=en-US");
        endpoints.put(ApiCallType.TV_TOPRATED,"https://api.themoviedb.org/3/tv/top_rated");
        endpoints.put(ApiCallType.MOVIE_TOPRATED,"https://api.themoviedb.org/3/movie/top_rated");
        endpoints.put(ApiCallType.MOVIE_UPCOMING,"https://api.themoviedb.org/3/movie/upcoming");
        endpoints.put(ApiCallType.MOVIE_TRENDING_WEEK,"https://api.themoviedb.org/3/trending/movie/week?language=en-US");
    }


    public void batchApiCall(ApiCallType callType, CountDownLatch latch) throws IOException { //TMDB API Handler
        String url = endpoints.get(callType);
        if (jsonCache.getJsonCache(callType) != null) {
            synchronized (responseData){
                responseData.put(callType, jsonCache.getJsonCache(callType));
            }
            latch.countDown();
        } else {
            Thread networkThread = new Thread(() -> {
            try {
                Request request = new Request.Builder()
                        .url(url)
                        .addHeader("accept", "application/json")
                        .addHeader("Authorization", AppConfig.getTMDBKey())
                        .build();

                Response response = client.newCall(request).execute();

                ResponseBody responseBody = response.body();
                assert responseBody != null;
                String responseBodyString = responseBody.string();

                jsonCache.setJsonCache(callType, responseBodyString);
                synchronized (responseData) {
                    responseData.put(callType, responseBodyString);
                }
                latch.countDown();
            } catch (IOException e){
                e.printStackTrace();
                latch.countDown();
            }
            });
            networkThread.start();
        }
    }

    public void onDemandApiCall(String url, ResponseHandler responseHandler){ //TMDB API Handler
        Thread networkThread = new Thread(() -> {
            Response response;
            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("accept", "application/json")
                    .addHeader("Authorization", AppConfig.getTMDBKey())
                    .build();
            try{
                response = client.newCall(request).execute();
                ResponseBody responseBody = response.body();
                assert responseBody != null;
                responseHandler.handleResponse(responseBody.string());
            } catch (IOException e){
                throw new RuntimeException();
            }
        });
        networkThread.start();
    }

    public void fetchData(Runnable callback){
        final CountDownLatch latch = new CountDownLatch(endpoints.size());
        endpoints.keySet().forEach(callType -> {
            try {
                batchApiCall(callType, latch);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Platform.runLater(callback);
    }

    public static String getResponseData(ApiCallType callType){
        synchronized (responseData) {
            return responseData.get(callType);
        }
    }

    public interface ResponseHandler{
        void handleResponse(String jsonResponse);

    }

}
