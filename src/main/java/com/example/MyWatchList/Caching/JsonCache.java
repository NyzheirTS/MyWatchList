package com.example.MyWatchList.Caching;

import com.example.MyWatchList.DataModels.ApiCallType;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public class JsonCache {
    private static final String CACHE_DIRECTORY = "Cache/json_cache";
    private static final Cache<String, String> apiJsonCache = Caffeine.newBuilder().maximumSize(100).build();
    private static final File cacheDirectory = new File(CACHE_DIRECTORY);
    private static final Date date = new Date();

    //TODO: Implement method for deleting cache after season is over for the info page. only delete on shutdown.


    static {
        try {
            if (!cacheDirectory.exists()){
                boolean isDirCreated = cacheDirectory.mkdirs();
                if(!isDirCreated){
                    System.out.println("Failed to create cache directory: " + cacheDirectory);
                }
            }
            removeOldFromCache();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void removeOldFromCache() throws IOException {
        for (File file : Objects.requireNonNull(cacheDirectory.listFiles())){
            LocalDate fileDate = LocalDate.parse(file.getName().substring(0,10), DateTimeFormatter.ofPattern("MM-dd-yyyy"));
            LocalDate todayDate = LocalDate.now();
            if (fileDate.isBefore(todayDate) && !file.isDirectory()){
                java.nio.file.Files.delete(file.toPath());
            }
        }
    }

    private String formatFilePath(String fileName){
        return CACHE_DIRECTORY + File.separator +
                new SimpleDateFormat("MM-dd-yyyy_").format(date) + fileName;
    }


    public  String getJsonCache(ApiCallType apiCallType) throws IOException {
        String fileName = apiCallType.name() + ".json";
        String localFilePath = formatFilePath(fileName);


        String apiData = apiJsonCache.getIfPresent(localFilePath);
        File cachedJson = new File(localFilePath);
        if(cachedJson.exists()) {
            byte[] byteData = Files.readAllBytes(cachedJson.toPath());
            apiData = new String(byteData, StandardCharsets.UTF_8);
            //apiJsonCache.put(localFilePath, apiData);
        }
        return apiData;
    }


    public void setJsonCache(ApiCallType apiCallType, String apiInfo) throws IOException {
        String fileName = apiCallType.name() + ".json";
        String localFilePath = formatFilePath(fileName);

        File cachedJson = new File(localFilePath);
        try (InputStream inputStream = new ByteArrayInputStream(apiInfo.getBytes(StandardCharsets.UTF_8));
             FileOutputStream outputStream = new FileOutputStream(cachedJson)) {

            byte[] buffer = new byte[4000];
            int byteReader;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            while ((byteReader = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, byteReader);
            }

            outputStream.write(byteArrayOutputStream.toByteArray());

            String apiData = String.valueOf(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));

            apiJsonCache.put(localFilePath, apiData);

        }
    }
}
