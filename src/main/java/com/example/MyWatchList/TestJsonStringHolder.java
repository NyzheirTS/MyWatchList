package com.example.MyWatchList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestJsonStringHolder {

    public static String getJsonString() throws IOException {
        String filepath = "C:\\Users\\eshas\\IdeaProjects\\MyWatchList\\src\\main\\java\\com\\example\\MyWatchList\\json.txt";
        return Files.readString(Paths.get(filepath));
    }

}
