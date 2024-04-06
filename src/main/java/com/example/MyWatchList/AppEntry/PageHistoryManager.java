package com.example.MyWatchList.AppEntry;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.Deque;

public class PageHistoryManager {
    private final Deque<WeakReference<Node>> backHistory = new ArrayDeque<>();
    private final Deque<WeakReference<Node>> forwardHistory = new ArrayDeque<>();
    private final BorderPane mainPane;
    private final Button backButton;
    private final Button forwardButton;

    public PageHistoryManager(BorderPane mainPane, Button backButton, Button forwardButton) {
        this.mainPane = mainPane;
        this.backButton = backButton;
        this.forwardButton = forwardButton;
        updateButtonStates();
    }

    //TODO: look into serializing node to better filter them
    public void navigateTo(Node page) {
        Node lastPage = mainPane.getCenter();
        if (lastPage != null && lastPage != page) {
            backHistory.push(new WeakReference<>(lastPage));
        }
        forwardHistory.clear();
        setCenterPage(page);
        System.out.println("Back History: " + backHistory);
        System.out.println("Forward History: " + forwardHistory);
    }

    public void goBack() {
        if (!backHistory.isEmpty()) {
            WeakReference<Node> previousPageRef = backHistory.pop();
            Node previousPage = previousPageRef.get();
            if (previousPage != null) {
                if (mainPane.getCenter() != null) {
                    forwardHistory.push(new WeakReference<>(mainPane.getCenter()));
                }
                setCenterPage(previousPage);
            }
        }
        logHistory();
    }

    public void goForward() {
        if (!forwardHistory.isEmpty()) {
            WeakReference<Node> nextPageRef = forwardHistory.pop();
            Node nextPage = nextPageRef.get();
            if (nextPage != null) {
                if (mainPane.getCenter() != null) {
                    backHistory.push(new WeakReference<>(mainPane.getCenter()));
                }
                setCenterPage(nextPage);
            }
        }
        logHistory();
    }

    private void setCenterPage(Node page) {
        Platform.runLater(() -> {
            mainPane.setCenter(page);
            updateButtonStates();
        });
    }

    private void updateButtonStates() {
        backButton.setDisable(backHistory.isEmpty());
        forwardButton.setDisable(forwardHistory.isEmpty());
    }
    private void logHistory() {
        System.out.println("Back History: " + backHistory.size());
        System.out.println("Forward History: " + forwardHistory.size());
    }

}

