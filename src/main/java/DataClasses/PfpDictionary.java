package DataClasses;

import java.util.HashMap;
import java.util.Map;

public class PfpDictionary {
    private Map<String,String> dictionary;


    public PfpDictionary(){
        dictionary = new HashMap<>();
    }

    public void Dictionary(){
        dictionary.put("pfp_0","C:\\Users\\eshas\\IdeaProjects\\Test_UI-JFX\\src\\main\\resources\\com\\example\\MyWatchList\\Fake PFP\\0.jpg");
        dictionary.put("pfp_1","C:\\Users\\eshas\\IdeaProjects\\Test_UI-JFX\\src\\main\\resources\\com\\example\\MyWatchList\\Fake PFP\\1.jpg");
        dictionary.put("pfp_2","C:\\Users\\eshas\\IdeaProjects\\Test_UI-JFX\\src\\main\\resources\\com\\example\\MyWatchList\\Fake PFP\\2.jpg");
        dictionary.put("pfp_3","C:\\Users\\eshas\\IdeaProjects\\Test_UI-JFX\\src\\main\\resources\\com\\example\\MyWatchList\\Fake PFP\\3.jpg");
        dictionary.put("pfp_4","C:\\Users\\eshas\\IdeaProjects\\Test_UI-JFX\\src\\main\\resources\\com\\example\\MyWatchList\\Fake PFP\\4.jpg");
        dictionary.put("pfp_5","C:\\Users\\eshas\\IdeaProjects\\Test_UI-JFX\\src\\main\\resources\\com\\example\\MyWatchList\\Fake PFP\\5.jpg");
        dictionary.put("pfp_6","C:\\Users\\eshas\\IdeaProjects\\Test_UI-JFX\\src\\main\\resources\\com\\example\\MyWatchList\\Fake PFP\\6.jpg");
        dictionary.put("pfp_7","C:\\Users\\eshas\\IdeaProjects\\Test_UI-JFX\\src\\main\\resources\\com\\example\\MyWatchList\\Fake PFP\\7.jpg");
        dictionary.put("pfp_8","C:\\Users\\eshas\\IdeaProjects\\Test_UI-JFX\\src\\main\\resources\\com\\example\\MyWatchList\\Fake PFP\\8.jpg");
    }


    public String getPIC(String key){
        return dictionary.get(key);
    }





}
