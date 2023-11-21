package com.example.MyWatchList.DataClasses;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {
    private static Properties properties;

    static {
        properties = new Properties();
        try(InputStream input = new FileInputStream("src\\main\\resources\\com\\example\\MyWatchList\\DataClasses\\config.properties")) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getTMDBKey(){
        return properties.getProperty("TMDB_APIKEY");
    }
}
