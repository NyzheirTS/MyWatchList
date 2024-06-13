package com.example.MyWatchList.TestFolder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestJsonStringHolder {
    private TestJsonStringHolder(){}

    public static String getJsonStringMovie() throws IOException {
        String filepath = "C:\\Users\\eshas\\IdeaProjects\\MyWatchList\\src\\main\\java\\com\\example\\MyWatchList\\TestFolder\\json.txt";
        return Files.readString(Paths.get(filepath));
    }
    public static String getJsonStringTV() throws IOException {
        String filepath = "C:\\Users\\eshas\\IdeaProjects\\MyWatchList\\src\\main\\java\\com\\example\\MyWatchList\\TestFolder\\jsonTV.txt";
        return Files.readString(Paths.get(filepath));
    }
    public static String getJsonStringActorActress() throws IOException {
        String filepath = "C:\\Users\\eshas\\IdeaProjects\\MyWatchList\\src\\main\\java\\com\\example\\MyWatchList\\TestFolder\\PersonPage.txt";
        return Files.readString(Paths.get(filepath));
    }

}
