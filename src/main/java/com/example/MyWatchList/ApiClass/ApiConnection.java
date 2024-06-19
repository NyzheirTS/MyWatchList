package com.example.MyWatchList.ApiClass;
import com.example.MyWatchList.AppConfig.AppConfig;
import com.example.MyWatchList.Caching.JsonCache;
import com.example.MyWatchList.Caching.TempDevJsonCache;
import com.example.MyWatchList.DataModels.Utils.ApiCallType;
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

    private static ApiConnection instance;
    private static final OkHttpClient client = new OkHttpClient();
    private static final Map<ApiCallType, String> responseData = new EnumMap<>(ApiCallType.class);
    private final Map<ApiCallType, String> endpoints = new EnumMap<>(ApiCallType.class);
    private final JsonCache jsonCache = new JsonCache();
    private final TempDevJsonCache tempDevJsonCache = new TempDevJsonCache();

    public ApiConnection() {
        initEndpoints();
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
        if (tempDevJsonCache.getJsonCache(callType) != null) {
            synchronized (responseData){
                responseData.put(callType, tempDevJsonCache.getJsonCache(callType));
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

                tempDevJsonCache.setJsonCache(callType, responseBodyString);
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

    public String onDemandApiCall(String url) throws IOException {
        final CountDownLatch latch = new CountDownLatch(1);
        final String[] result = new String[1]; // Use an array to hold the response since lambda requires final or effectively final variables
        if (jsonCache.getJsonCache(url) != null){
            return jsonCache.getJsonCache(url);
        } else {
            Thread networkThread = new Thread(() -> {
            try {
                Request request = new Request.Builder()
                        .url(url)
                        .addHeader("accept", "application/json")
                        .addHeader("Authorization", AppConfig.getTMDBKey())
                        .build();
                Response response = client.newCall(request).execute();

                if (!response.isSuccessful()) {
                    result[0] = "Error: Server responded with status " + response.code();
                    return;
                }
                ResponseBody responseBody = response.body();
                assert responseBody != null;
                String responseBodyString = responseBody.string();

                jsonCache.setJsonCache(url, responseBodyString);
                result[0] = responseBodyString;
                } catch (IOException e) {
                    result[0] = "Error: Network request failed due to an IOException";
                } finally {
                    latch.countDown(); // Decrease the count of the latch, releasing all waiting threads
                }
            });
            networkThread.start();
            try {
                latch.await(); // Wait for the network thread to finish
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Set the interrupt flag again
                return "Error: Thread was interrupted";
            }
        }
        return result[0];
    }

    public static String getResponseData(ApiCallType callType){
        synchronized (responseData) {
            return responseData.get(callType);
        }
    }

    public static ApiConnection getInstance(){
        if (ApiConnection.instance == null){
            ApiConnection.instance = new ApiConnection();
        }
        return instance;
    }

}
