package com.example.MyWatchList.NodeClasses;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class Carousel {
        private List<Node> nodes = new ArrayList<>();
        private int currPage = 0;
        private final int itemsVisible;
        private final HBox displayBox;

        public Carousel(HBox displayBox, int itemsVisible) {
            this.displayBox = displayBox;
            this.itemsVisible = itemsVisible;
        }

        public void addItem(Node item) {
            nodes.add(item);
        }

        public void updateDisplay() {
            Platform.runLater(() -> {
                displayBox.getChildren().clear();
                int start = currPage * itemsVisible;
                int end = Math.min(start + itemsVisible, nodes.size());
                for (int i = start; i < end; i++) {
                    displayBox.getChildren().add(nodes.get(i));
                }
            });
        }

        public void navigate(int direction) {
            int totalPages = (int) Math.ceil((double) nodes.size() / itemsVisible);
            currPage += direction;
            currPage = Math.max(0, Math.min(currPage, totalPages - 1));
            updateDisplay();
        }
}


