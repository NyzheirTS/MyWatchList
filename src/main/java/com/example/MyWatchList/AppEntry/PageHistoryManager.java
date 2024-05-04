package com.example.MyWatchList.AppEntry;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class PageHistoryManager {
    private final StaticPageHistory history = new StaticPageHistory();
    private final BorderPane mainPane;
    private final Button backButton;
    private final Button forwardButton;

    public PageHistoryManager(BorderPane mainPane, Button backButton, Button forwardButton) {
        this.mainPane = mainPane;
        this.backButton = backButton;
        this.forwardButton = forwardButton;
        updateButtonStates();
    }

    public void navigateTo(Node page) {
        history.setNode(mainPane.getCenter());
        history.setHistory(page);
        setCenterPage(history.getNode());
        history.logHistory();
    }
    public void navigateTot(Node page) {
        history.setNode(mainPane.getCenter());
        history.setHistory(page);
    }



    public void goBack() {
        history.goBack();
        setCenterPage(history.getNode());
        history.logHistory();
    }

    public void goForward() {
        history.goForward();
        setCenterPage(history.getNode());
        history.logHistory();
    }

    private void setCenterPage(Node page) {
        Platform.runLater(() -> {
            mainPane.setCenter(page);
            updateButtonStates();
        });
    }

    private void updateButtonStates() {
        backButton.setDisable(!history.canGoBack());
        forwardButton.setDisable(!history.canGoForward());
    }

}

