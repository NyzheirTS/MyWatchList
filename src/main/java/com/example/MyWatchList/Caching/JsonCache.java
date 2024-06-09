package com.example.MyWatchList.Caching;

import com.example.MyWatchList.AppConfig.AppConfig;
import com.example.MyWatchList.DataModels.ApiCallType;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;

public class JsonCache {
    private static final String CACHE_DIRECTORY = "Cache/json_cache";
    //private static final Cache<String, String> apiJsonCache = Caffeine.newBuilder().maximumSize(100).build();
    private static final File cacheDirectory = new File(CACHE_DIRECTORY);


    static {
        if (!cacheDirectory.exists()){
            boolean isDirCreated = cacheDirectory.mkdirs();
            if(!isDirCreated){
                System.out.println("Failed to create cache directory: " + cacheDirectory);
            }
        }
    }

    private String formatName(String name){
        return
                CACHE_DIRECTORY + File.separator + name.replaceAll("[^a-zA-Z0-9]", "_") + ".json";
    }


    public String getJsonCache(String url) throws IOException {
        String fileName = formatName(url);

        //String apiData = apiJsonCache.getIfPresent(fileName);
        String apiData = null;
        File cachedJson = new File(fileName);
        if(cachedJson.exists()) {
            byte[] byteData = Files.readAllBytes(cachedJson.toPath());
            apiData = new String(byteData, StandardCharsets.UTF_8);
            //apiJsonCache.put(localFilePath, apiData);
        }
        return apiData;
    }


    public void setJsonCache(String url, String apiInfo) throws IOException {
        //String localFilePath = formatName(url);

        File cachedJson = new File(formatName(url));
        // Write the json string to the file
        try (InputStream inputStream = new ByteArrayInputStream(apiInfo.getBytes(StandardCharsets.UTF_8));
             FileOutputStream outputStream = new FileOutputStream(cachedJson)) {

            byte[] buffer = new byte[4000];
            int byteReader;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            while ((byteReader = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, byteReader);
            }

            outputStream.write(byteArrayOutputStream.toByteArray());

            //String apiData = String.valueOf(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));

            //apiJsonCache.put(localFilePath, apiData);
        }
    }

    /*
    public static void printCache(){
        System.out.println(apiJsonCache.estimatedSize());
        System.out.println(apiJsonCache.asMap());
    }
    */

    public static void clearCacheOnShutdown() throws IOException {
        for (File file : Objects.requireNonNull(cacheDirectory.listFiles())){
            if (!file.isDirectory()){
                java.nio.file.Files.delete(file.toPath());
            }
        }
    }
}


