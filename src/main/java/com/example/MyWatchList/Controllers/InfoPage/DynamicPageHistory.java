package com.example.MyWatchList.Controllers.InfoPage;

import com.example.MyWatchList.AppEntry.Pair;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

public class DynamicPageHistory {
    private final Deque<Pair<Integer, String>> back = new ArrayDeque<>();
    private final Button backButton;

    private final InfoPageController controller = new InfoPageController();
    public DynamicPageHistory(Button button){
        this.backButton = button;
    }

    public void addBack(Pair<Integer,String> pair){
        back.push(pair);
        updateButtonState();
        System.out.println(back);
    }

    public void goback(){
        if (!back.isEmpty()) {
            Pair<Integer, String> lastpair = back.pop();
            if (lastpair != null) {
                try {
                    controller.updatePage(lastpair.getFirst(), lastpair.getSecond());
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

    }


    private void updateButtonState(){
        backButton.setDisable(back.isEmpty());
    }



}
