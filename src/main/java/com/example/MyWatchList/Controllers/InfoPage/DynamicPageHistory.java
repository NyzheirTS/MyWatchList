package com.example.MyWatchList.Controllers.InfoPage;

import com.example.MyWatchList.AppEntry.Pair;

import java.util.ArrayDeque;
import java.util.Deque;

public class DynamicPageHistory {
    private final Deque<Pair<Integer, String>> back = new ArrayDeque<>();
    private Pair<Integer, String> lastPair;
    private Boolean historyInit = false;

    public void addBack(Pair<Integer,String> pair){
        if (Boolean.TRUE.equals(historyInit)){
            back.push(lastPair);
            lastPair = pair;
        } else {
            lastPair = pair;
            historyInit = true;
        }
    }

    public Pair<Integer, String> goBack(){
        if (!back.isEmpty()) {
            return back.pop();
        }
        return null;
    }

    public Boolean historyIsEmpty(){
        return back.isEmpty();
    }



}
