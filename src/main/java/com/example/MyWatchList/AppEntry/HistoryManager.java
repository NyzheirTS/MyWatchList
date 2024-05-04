package com.example.MyWatchList.AppEntry;

import javafx.scene.Node;

public interface HistoryManager {
    public void setHistory(Node node);
    public void goBack();
    public void goForward();
    public boolean canGoBack();
    public boolean canGoForward();
    public Node getNode();
    public void setNode(Node node);
}
