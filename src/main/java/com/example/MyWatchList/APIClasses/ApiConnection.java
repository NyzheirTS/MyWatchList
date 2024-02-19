package com.example.MyWatchList.APIClasses;
import com.example.MyWatchList.DataClasses.AppConfig;
import javafx.application.Platform;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

public class ApiConnection {
    private final OkHttpClient client;
    private static final HashMap<ApiCallType, String> responseData = new HashMap<>();
    private final HashMap<ApiCallType, String> endpoints = new HashMap<>();

    public ApiConnection() {
        initEndpoints();
        this.client = new OkHttpClient();
    }

    private void initEndpoints(){
        endpoints.put(ApiCallType.TV_UPCOMING,"https://api.themoviedb.org/3/tv/upcoming");
        endpoints.put(ApiCallType.TV_TRENDING_WEEK,"https://api.themoviedb.org/3/trending/tv/week?language=en-US");
        endpoints.put(ApiCallType.TV_TOPRATED,"https://api.themoviedb.org/3/tv/top_rated");
        endpoints.put(ApiCallType.MOVIE_TOPRATED,"https://api.themoviedb.org/3/movie/top_rated");
        endpoints.put(ApiCallType.MOVIE_UPCOMING,"https://api.themoviedb.org/3/movie/upcoming");
        endpoints.put(ApiCallType.MOVIE_TRENDING_WEEK,"https://api.themoviedb.org/3/trending/movie/week?language=en-US");
    }

    public enum ApiCallType {
        TV_TRENDING_WEEK,
        MOVIE_TRENDING_WEEK,
        TV_UPCOMING,
        MOVIE_UPCOMING,
        TV_TOPRATED,
        MOVIE_TOPRATED,
    }

    public void batchApiCall(ApiCallType callType, CountDownLatch latch){ //TMDB API Handler
        String url = endpoints.get(callType);
        Thread networkThread = new Thread(() -> {
            try {
            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("accept", "application/json")
                    .addHeader("Authorization", AppConfig.getTMDBKey())
                    .build();

                Response response = client.newCall(request).execute();
                ResponseBody responseBody = response.body();
                if(responseBody != null){
                    synchronized (responseData){
                        responseData.put(callType, responseBody.string());
                    }
                }
                latch.countDown();
            } catch (IOException e){
                e.printStackTrace();
                latch.countDown();
            }
        });
        networkThread.start();

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
        endpoints.keySet().forEach(callType -> batchApiCall(callType, latch));

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
