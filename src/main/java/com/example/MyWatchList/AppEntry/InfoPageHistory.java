package com.example.MyWatchList.AppEntry;

import com.example.MyWatchList.Controllers.InfoPage.InfoPageController;
import javafx.scene.Node;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

public class InfoPageHistory implements HistoryManager{

    private final Deque<Pair<Integer, String>> backHistory = new ArrayDeque<>();
    private final Deque<Pair<Integer, String>> forwardHistory = new ArrayDeque<>();
    private InfoPageController infoPageController;
    private Node thisNode;

    @Override
    public void setHistory(Node node) {
        if (thisNode != null && thisNode.getProperties().containsKey("controller")){
            infoPageController = getInfoPageController();
            backHistory.push(new Pair<>(infoPageController.getNodeID(), infoPageController.getMediaType()));
        }
        forwardHistory.clear();
        thisNode = node;
    }

    @Override
    public void goBack() {
        if (!backHistory.isEmpty()) {
            Pair<Integer, String> previousPair = backHistory.pop();
            if (previousPair != null) {
                updatePage(previousPair);
                forwardHistory.push(new Pair<>(infoPageController.getNodeID(), infoPageController.getMediaType()));
            }
        }
    }

    @Override
    public void goForward() {
        if (!forwardHistory.isEmpty()) {
            Pair<Integer, String> nextPair = forwardHistory.pop();
            if (nextPair != null) {
                updatePage(nextPair);
                backHistory.push(new Pair<>(infoPageController.getNodeID(), infoPageController.getMediaType()));
            }
        }
    }

    @Override
    public boolean canGoBack() {
        return !backHistory.isEmpty();
    }

    @Override
    public boolean canGoForward() {
        return !forwardHistory.isEmpty();
    }

    @Override
    public Node getNode() {
        return thisNode;
    }

    @Override
    public void setNode(Node node) {
        thisNode = node;
    }

    @Override
    public void logHistory() {
        System.out.println("Back History: " + backHistory);
        System.out.println("Forward History: " + forwardHistory);
    }

    private void updatePage(Pair<Integer, String> pair){
        try {
            infoPageController = getInfoPageController();
            infoPageController.updatePage(pair.getFirst(), pair.getSecond());
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    private InfoPageController getInfoPageController(){
       return (InfoPageController) thisNode.getProperties().get("controller");
    }

}
