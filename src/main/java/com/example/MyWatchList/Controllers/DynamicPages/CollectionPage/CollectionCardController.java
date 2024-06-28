package com.example.MyWatchList.Controllers.DynamicPages.CollectionPage;

import com.example.MyWatchList.Controllers.CommonComponent.CommonFormatter;
import com.example.MyWatchList.DataModels.CollectionModels.CollectionModel;
import com.example.MyWatchList.DataModels.Utils.UrlBuilder;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class CollectionCardController {

    @FXML private ImageView posterImg;
    @FXML private ImageView backgroundImage;
    @FXML private Label title;
    @FXML private Label rate;
    @FXML private Label date;
    @FXML private StackPane base;




    public void createCard(CollectionModel.Parts data){
        setBackgroundImage(data.getBackdrop_path());
        setPosterImage(data.getPoster_path());
        setLabels(data);
    }


    private void setAction(){
        base.setOnMouseClicked(event -> {
            //dodo
        });
    }

    private void setBackgroundImage(String url){
        backgroundImage.setImage(null);
        Task<Object> imageLoadingTask = new Task<>() {
            @Override
            protected Void call() {
                Image loadedImage = new Image(UrlBuilder.getBackDropImageURL(url), true);
                Platform.runLater(() -> backgroundImage.setImage(loadedImage));
                return null;
            }
        };
        new Thread(imageLoadingTask).start();
    }

    private void setPosterImage(String url){
        posterImg.setImage(null);
        Task<Object> imageLoadingTask = new Task<>() {
            @Override
            protected Void call() {
                Image loadedImage = new Image(UrlBuilder.getBackDropImageURL(url), true);
                Platform.runLater(() -> posterImg.setImage(loadedImage));
                return null;
            }
        };
        new Thread(imageLoadingTask).start();
    }

    private void setLabels(CollectionModel.Parts data){
        Platform.runLater(() -> {
            title.setText(data.getOriginal_title());
            rate.setText(String.valueOf(CommonFormatter.round(data.getVote_average(), 2)));
            date.setText(data.getRelease_date());
        });
    }

}
