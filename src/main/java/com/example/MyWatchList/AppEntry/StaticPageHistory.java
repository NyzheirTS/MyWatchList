package com.example.MyWatchList.AppEntry;

import javafx.scene.Node;

import java.util.ArrayDeque;
import java.util.Deque;

public class StaticPageHistory implements HistoryManager{
    private final Deque<Node> backHistory = new ArrayDeque<>();
    private final Deque<Node> forwardHistory = new ArrayDeque<>();
    private Node thisNode;

    @Override
    public void setHistory(Node node) {
        if (thisNode != null && thisNode != node) {
            backHistory.push(thisNode);
        }
        forwardHistory.clear();
        thisNode = node;
    }

    @Override
    public void goBack() {
        if (!backHistory.isEmpty()) {
            Node previousNode = backHistory.pop();
            if (previousNode != null) {
                forwardHistory.push(thisNode);
                thisNode = previousNode;
            }
        }
    }

    @Override
    public void goForward() {
        if (!forwardHistory.isEmpty()) {
            Node nextNode = forwardHistory.pop();
            if (nextNode != null) {
                backHistory.push(thisNode);
                thisNode = nextNode;
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
    public void logHistory(){
        System.out.println("Back History: " + backHistory);
        System.out.println("Forward History: " + forwardHistory);
    }
}
