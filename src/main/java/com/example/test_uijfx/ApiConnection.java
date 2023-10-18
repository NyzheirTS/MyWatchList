package com.example.test_uijfx;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


import java.io.IOException;

public class ApiConnection {
    private final OkHttpClient client;

    public ApiConnection() {
        this.client = new OkHttpClient();
    }

    public void getRequestAsync(String url, ResponseHandler responseHandler){ //TMDB API Handler
        Thread networkThread = new Thread(() -> {
            Response response;
            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("accept", "application/json")
                    .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzNGFmODdhYTY2ZDdkZDAzNTYyODQwODlkYmM3NmViMSIsInN1YiI6IjY1MmMzNTM1ZjI4ODM4MDJhMjVlYjY1NyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.UZfksL9d--GEFpBTuARhdzkyHqGICS8c1z5R70SpSZk")
                    .build();
            try{
                response = client.newCall(request).execute();
                ResponseBody responseBody = response.body();
                responseHandler.handleResponse(responseBody.string());

            } catch (IOException e){
                throw new RuntimeException();
            }
        });
        networkThread.start();
    }
    public interface ResponseHandler{
        void handleResponse(String jsonResponse);
    }

}
