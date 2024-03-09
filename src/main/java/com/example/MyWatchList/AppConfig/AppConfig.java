package com.example.MyWatchList.AppConfig;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {
    private static final Properties properties;
    private AppConfig(){}

    static {
        properties = new Properties();
        try(InputStream input = new FileInputStream("src\\main\\resources\\com\\example\\MyWatchList\\AppConfig\\config.properties")) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getTMDBKey(){
        return properties.getProperty("TMDB_APIKEY");
    }
}
