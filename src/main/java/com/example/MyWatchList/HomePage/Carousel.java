package com.example.MyWatchList.HomePage;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class Carousel {
        private final List<PosterNode> nodes = new ArrayList<>();
        private int currPage = 0;
        private final int itemsVisible = 5;
        private final HBox displayBox;

        public Carousel(HBox displayBox) {
            this.displayBox = displayBox;
        }

        public void addItem(PosterNode item) {
            nodes.add(item);
        }

        public void updateDisplay() {
            Platform.runLater(() -> {
                displayBox.getChildren().clear();
                int start = currPage * itemsVisible;
                int end = Math.min(start + itemsVisible, nodes.size());
                for (int i = start; i < end; i++) {
                    displayBox.getChildren().add(nodes.get(i).getNode());
                    nodes.get(i).loadImg();
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


