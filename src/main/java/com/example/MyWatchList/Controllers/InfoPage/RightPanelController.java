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

    public void updateRightPanel(MediaInfoPageModel jsonString, String MediaType){
        Platform.runLater(() -> {
            buildVbox1(jsonString);
            buildVbox2(jsonString);
            rightLabel.setText(formatLabel(MediaType));
        });
    }



    private void buildVbox1(MediaInfoPageModel jsonString){
        vbox1.getChildren().clear();
        RecommendationsModel.Recommendations[] recommendations = jsonString.getRecommendations().getResults();
        for (int i = 0; i <= 9; i++){
            AnchorPane node = CommonFactory.createPosterNode(
                    recommendations[i].getPoster_path(),
                    recommendations[i].getId(),
                    recommendations[i].getTitleMovie(),
                    recommendations[i].getMedia_type(),
                    false,
                    false,
                    true
            );
            configurePosterNode(node);
            vbox1.getChildren().add(node);
        }
    }

    private void buildVbox2(MediaInfoPageModel jsonString){
        vbox2.getChildren().clear();
        RecommendationsModel.Recommendations[] recommendations = jsonString.getRecommendations().getResults();
        for (int i = 10; i <= 19; i++){
            AnchorPane node = CommonFactory.createPosterNode(
                    recommendations[i].getPoster_path(),
                    recommendations[i].getId(),
                    recommendations[i].getTitleMovie(),
                    recommendations[i].getMedia_type(),
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
        return (PosterNodeController) node.getProperties().get("controller");
    }

}
