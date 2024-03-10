package com.example.MyWatchList.HomePage;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class Carousel {
        private final List<PosterNode> nodes = new ArrayList<>();
        private int currPage = 0;
        private static final int itemsVisible = 5;
        private final HBox displayBox;
        private final Button forward;
        private final Button backward;


        public Carousel(HBox displayBox, Button forward, Button backward) {
            this.displayBox = displayBox;
            this.forward = forward;
            this.backward = backward;
        }

        public void addItem(PosterNode item) {
            nodes.add(item);
        }

        public void updateDisplay() {
            Platform.runLater(() -> {
                displayBox.getChildren().clear();
                updateButtons();

                int start = currPage * itemsVisible;
                int end = Math.min(start + itemsVisible, nodes.size());

                int prevPageStart = Math.max(0, (currPage - 1) * itemsVisible);
                int nextPageEnd = Math.min((currPage + 2) * itemsVisible, nodes.size());

                for (int i = start; i < end; i++) {
                    displayBox.getChildren().add(nodes.get(i).getNode());
                    nodes.get(i).loadImg();
                }

                loadImages(prevPageStart,nextPageEnd,start,end);
                unloadImages(prevPageStart,nextPageEnd);
            });
        }
        private void unloadImages(int prev, int next){ // unload the images that are out of view within a 1 page radius IE. if im on page i go to page 3 pages 0 and 1 get unloaded.
            for (int i = 0; i < prev; i++){
                nodes.get(i).unloadImg();
            }
            for(int i = next; i < nodes.size(); i++){
                nodes.get(i).unloadImg();
            }
        }

        private void loadImages(int prev, int next, int start, int end){ //preloads the images before and after the current page IE. if im on page 1 pages 0 and 2 are loaded
            for (int i = prev; i < start; i++){
                nodes.get(i).loadImg();
            }

            for (int i = end; i < next; i++){
                nodes.get(i).loadImg();
            }
        }

        public void navigate(int direction) {
            int totalPages = (int) Math.ceil((double) nodes.size() / itemsVisible);
            currPage += direction;
            currPage = Math.max(0, Math.min(currPage, totalPages - 1));
            updateDisplay();

        }

        private void updateButtons(){
            if (currPage == 0){
                forward.setVisible(true);
                backward.setVisible(false);
            }else if (currPage == 3){
                forward.setVisible(false);
                backward.setVisible(true);
            }else {
                forward.setVisible(true);
                backward.setVisible(true);
            }
        }


    public void clearCarousel(){
            displayBox.getChildren().clear();
        }
}


