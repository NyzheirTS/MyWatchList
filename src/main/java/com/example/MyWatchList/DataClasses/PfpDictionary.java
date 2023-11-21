package com.example.MyWatchList.DataClasses;

import java.util.HashMap;
import java.util.Map;

public class PfpDictionary {
    private Map<String,String> dictionary;


    public PfpDictionary(){
        dictionary = new HashMap<>();
    }

    public void Dictionary(){
        dictionary.put("pfp_0","C:\\Users\\eshas\\IdeaProjects\\MyWatchList\\src\\main\\resources\\com\\example\\MyWatchList\\Fake_PFP\\0.jpg");
        dictionary.put("pfp_1","C:\\Users\\eshas\\IdeaProjects\\MyWatchList\\src\\main\\resources\\com\\example\\MyWatchList\\Fake_PFP\\1.jpg");
        dictionary.put("pfp_2","C:\\Users\\eshas\\IdeaProjects\\MyWatchList\\src\\main\\resources\\com\\example\\MyWatchList\\Fake_PFP\\2.jpg");
        dictionary.put("pfp_3","C:\\Users\\eshas\\IdeaProjects\\MyWatchList\\src\\main\\resources\\com\\example\\MyWatchList\\Fake_PFP\\3.jpg");
        dictionary.put("pfp_4","C:\\Users\\eshas\\IdeaProjects\\MyWatchList\\src\\main\\resources\\com\\example\\MyWatchList\\Fake_PFP\\4.jpg");
        dictionary.put("pfp_5","C:\\Users\\eshas\\IdeaProjects\\MyWatchList\\src\\main\\resources\\com\\example\\MyWatchList\\Fake_PFP\\5.jpg");
        dictionary.put("pfp_6","C:\\Users\\eshas\\IdeaProjects\\MyWatchList\\src\\main\\resources\\com\\example\\MyWatchList\\Fake_PFP\\6.jpg");
        dictionary.put("pfp_7","C:\\Users\\eshas\\IdeaProjects\\MyWatchList\\src\\main\\resources\\com\\example\\MyWatchList\\Fake_PFP\\7.jpg");
        dictionary.put("pfp_8","C:\\Users\\eshas\\IdeaProjects\\MyWatchList\\src\\main\\resources\\com\\example\\MyWatchList\\Fake_PFP\\8.jpg");
    }


    public String getPIC(String key){
        return dictionary.get(key);
    }





}
