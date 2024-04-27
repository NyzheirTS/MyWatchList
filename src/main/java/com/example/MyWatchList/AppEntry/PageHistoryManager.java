package com.example.MyWatchList.AppEntry;

import com.example.MyWatchList.AppConfig.AppCleaner;
import com.example.MyWatchList.Controllers.InfoPage.InfoPageFactory;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.util.Pair;

import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class PageHistoryManager {
    private final Deque<Node> backHistory = new ArrayDeque<>();
    private final Deque<Node> forwardHistory = new ArrayDeque<>();

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
            backHistory.push(lastPage);
        }
        clearAndClean();
        Runtime.getRuntime().gc();
        setCenterPage(page);
        System.out.println("Back History: " + backHistory);
        System.out.println("Forward History: " + forwardHistory);
    }

    public void clearAndClean(){
        for (Node node : forwardHistory) {
            Object controller = node.getProperties().get("controller");
            if (controller instanceof  AppCleaner){
                ((AppCleaner) controller).cleanup();
                System.out.println("Clean");
            }
        }
        forwardHistory.clear();
    }

    public void goBack() {
        if (!backHistory.isEmpty()) {
            Node previousPage = backHistory.pop();
            if (previousPage != null) {
                if (mainPane.getCenter() != null) {
                    forwardHistory.push(mainPane.getCenter());
                }
                setCenterPage(previousPage);
            }
        }
        logHistory();
    }

    public void goForward() {
        if (!forwardHistory.isEmpty()) {
            Node nextPage = forwardHistory.pop();
            if (nextPage != null) {
                if (mainPane.getCenter() != null) {
                    backHistory.push(mainPane.getCenter());
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
        System.out.println("Back History: " + backHistory);
        System.out.println("Forward History: " + forwardHistory);
    }

}

