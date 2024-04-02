package com.example.MyWatchList.Controllers.InfoPage;

import com.example.MyWatchList.Controllers.CommonComponent.CommonFactory;
import com.example.MyWatchList.Controllers.CommonComponent.PosterNodeController;
import com.example.MyWatchList.DataModels.CommonModels.MediaInfoPageModel;
import com.example.MyWatchList.DataModels.CommonModels.RecommendationsModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class RightPanelController {

    @FXML private Label rightLabel;
    @FXML private VBox vbox1;
    @FXML private VBox vbox2;
    private MediaInfoPageModel jsonString;
    private String mediatype;

    public void initRightPanel(MediaInfoPageModel jsonString, String MediaType){
        this.jsonString = jsonString;
        this.mediatype = MediaType;
        buildPanel();
    }

    private void buildPanel(){
        Platform.runLater(() -> {
            buildVbox1();
            buildVbox2();
            rightLabel.setText(formatLabel(mediatype));
        });
    }

    private void buildVbox1(){
        RecommendationsModel.Recommendations[] movies = jsonString.getRecommendations().getResults();
        for (int i = 0; i <= 9; i++){
            AnchorPane node = CommonFactory.createPosterNode(
                    movies[i].getPoster_path(),
                    movies[i].getId(),
                    movies[i].getTitleMovie(),
                    movies[i].getMedia_type(),
                    false,
                    false,
                    true
            );
            configurePosterNode(node);
            vbox1.getChildren().add(node);
        }
    }

    private void buildVbox2(){
        RecommendationsModel.Recommendations[] movies = jsonString.getRecommendations().getResults();
        for (int i = 10; i <= 19; i++){
            AnchorPane node = CommonFactory.createPosterNode(
                    movies[i].getPoster_path(),
                    movies[i].getId(),
                    movies[i].getTitleMovie(),
                    movies[i].getMedia_type(),
                    false,
                    false,
                    true
            );
            configurePosterNode(node);
            vbox2.getChildren().add(node);
        }
    }


    private void configurePosterNode(AnchorPane node){
        getControllerFromNode(node).loadImg();
        getControllerFromNode(node).setPosterHeight(185);
        getControllerFromNode(node).setPosterWidth(131);
    }

    private String formatLabel(String type){
        if (type.equals("movie")) {
            return String.format("Recommended %ss", type.substring(0, 1).toUpperCase() + type.substring(1));
        } else if(type.equals("tv")){
            return String.format("Recommended %s Shows", type.toUpperCase());
        }
        return null;
    }
    private PosterNodeController getControllerFromNode(AnchorPane node){
        return (PosterNodeController) node.getProperties().get("posterController");
    }






}

