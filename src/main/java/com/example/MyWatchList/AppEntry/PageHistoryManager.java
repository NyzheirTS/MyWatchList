package com.example.MyWatchList.AppEntry;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.util.HashMap;

public class PageHistoryManager {
    private final HashMap<String, HistoryManager> map = new HashMap<>();
    private HistoryManager historyManager;
    private final BorderPane mainPane;
    private final Button backButton;
    private final Button forwardButton;

    public PageHistoryManager(BorderPane mainPane, Button backButton, Button forwardButton) {
        this.mainPane = mainPane;
        this.backButton = backButton;
        this.forwardButton = forwardButton;
        initMap();
        updateButtonStates();
    }

    private void initMap(){
        map.put("staticpage", new StaticPageHistory());
        map.put("infopage", new InfoPageHistory());
        this.historyManager = map.get("staticpage");
    }

    public void navigateTo(Node page) {
        getHistory();
        historyManager.setNode(mainPane.getCenter());
        historyManager.setHistory(page);
        setCenterPage(historyManager.getNode());
        historyManager.logHistory();
    }

    private void getHistory(){
        historyManager = map.get(mainPane.getCenter().getId());
        System.out.println("Was On " + mainPane.getCenter().getId());
    }

    public void goBack() {
        historyManager.goBack();
        if (!historyManager.canGoBack() && mainPane.getCenter().getId().equals("infopage")) {
            switchToStaticPageHistory();
        }
        setCenterPage(historyManager.getNode());
        historyManager.logHistory();
    }

    private void switchToStaticPageHistory() {
        historyManager = map.get("staticpage");
        setCenterPage(historyManager.getNode());
    }

    public void goForward() {
        historyManager.goForward();
        if(!historyManager.canGoForward() && mainPane.getCenter().getId().equals("infopage")){
            switchToStaticPageHistory();
        }
        setCenterPage(historyManager.getNode());
        historyManager.logHistory();
    }

    private void setCenterPage(Node page) {
        Platform.runLater(() -> {
            mainPane.setCenter(page);
            updateButtonStates();
        });
    }

    private void updateButtonStates() {
        backButton.setDisable(!historyManager.canGoBack());
        forwardButton.setDisable(!historyManager.canGoForward());
    }

}

