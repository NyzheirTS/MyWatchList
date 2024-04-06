package com.example.MyWatchList.Controllers.HomePage;

import com.example.MyWatchList.Controllers.CommonComponent.PosterNodeController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import java.util.ArrayList;
import java.util.List;

public class CarouselController {
    private final List<AnchorPane> nodes = new ArrayList<>();
    private int currPage = 0;
    private static final int itemsVisible = 5;
    @FXML
    private  Button forward;
    @FXML
    private  Button backward;
    @FXML
    private  HBox posterContainer;



    public void initCarousel(){
        setNavigation();
    }


    public void addItem(AnchorPane item) {nodes.add(item);}

    public void updateDisplay() {
        Platform.runLater(() -> {
            posterContainer.getChildren().clear();
            updateButtons();

            int start = currPage * itemsVisible;
            int end = Math.min(start + itemsVisible, nodes.size());


            for (int i = start; i < end; i++) {
                AnchorPane pnode = nodes.get(i);
                posterContainer.getChildren().add(pnode);
                getControllerFromNode(pnode).loadImg();
            }

            int prevPageStart = Math.max(0, (currPage - 1) * itemsVisible);
            int nextPageEnd = Math.min((currPage + 2) * itemsVisible, nodes.size());

            loadImages(prevPageStart,nextPageEnd,start,end);
            unloadImages(prevPageStart,nextPageEnd);
        });
    }
    private void unloadImages(int prev, int next){ // unload the images that are out of view within a 1-page radius IE. if im on page I go to page 3 pages 0 and 1 get unloaded.
        for (int i = 0; i < prev; i++){
            getControllerFromNode(nodes.get(i)).unloadImg();
        }
        for(int i = next; i < nodes.size(); i++){
            getControllerFromNode(nodes.get(i)).unloadImg();
        }
    }

    private void loadImages(int prev, int next, int start, int end){ //preloads the images before and after the current page IE. if im on page 1 pages 0 and 2 are loaded
        for (int i = prev; i < start; i++){
            getControllerFromNode(nodes.get(i)).loadImg();
        }

        for (int i = end; i < next; i++){
            getControllerFromNode(nodes.get(i)).loadImg();
        }
    }

    public void navigate(int direction) {
        int totalPages = (int) Math.ceil((double) nodes.size() / itemsVisible);
        currPage += direction;
        currPage = Math.max(0, Math.min(currPage, totalPages - 1));
        updateDisplay();

    }

    private void setNavigation(){
        forward.setOnAction(event -> navigate(1));
        backward.setOnAction(event -> navigate(-1));
    }

    private void updateButtons(){
        switch (currPage){
            case 0:
                forward.setVisible(true);
                backward.setVisible(false);
                break;
            case 3:
                forward.setVisible(false);
                backward.setVisible(true);
                break;
            default:
                forward.setVisible(true);
                backward.setVisible(true);
        }
    }

    private PosterNodeController getControllerFromNode(AnchorPane node){
        return (PosterNodeController) node.getProperties().get("controller");
    }

    public void clearCarousel(){posterContainer.getChildren().clear();}
}


