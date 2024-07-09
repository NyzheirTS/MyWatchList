package com.example.MyWatchList.Controllers.DynamicPages.SearchPage;

import com.example.MyWatchList.Controllers.CommonComponent.CommonFormatter;
import com.example.MyWatchList.Controllers.EventHandlers.InfoPageRequestEvent;
import com.example.MyWatchList.DataModels.Utils.UrlBuilder;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import java.io.FileInputStream;
import java.io.InputStream;

public class MediaSearchCardController {
    @FXML private BorderPane basePane;
    @FXML private ImageView posterImg;
    @FXML private Label nameLabel;
    @FXML private Label overviewLabel;
    @FXML private Label scoreLabel;

    private int id;
    private String mediaType;

    public void createCard(String imgURL, String name, String overview, double score, int id, String mediaType){
        this.id = id;
        this.mediaType = mediaType;
        setPosterImg(imgURL);
        setNameLabel(name);
        setOverviewLabel(overview);
        setScoreLabel(score);
        setOnAction();
    }

    private void setPosterImg(String url){
        posterImg.setImage(null);
        Task<Object> imageLoadingTask = new Task<>() {
            @Override
            protected Void call() {
                Image loadedImage = new Image(UrlBuilder.getBasePosterw92(url), true);
                Platform.runLater(() -> posterImg.setImage(loadedImage));
                return null;
            }
        };
        if (url != null) {
            new Thread(imageLoadingTask).start();
        } else {

        }
    }

    private void setNameLabel(String name){
        Platform.runLater(() -> nameLabel.setText(name));
    }

    private void setOverviewLabel(String overview){
        Platform.runLater(() -> overviewLabel.setText(overview));
    }

    private void setScoreLabel(double score){
        Platform.runLater(() -> scoreLabel.setText(String.valueOf(CommonFormatter.round(score,2))));
    }

    private void setOnAction(){
        basePane.setOnMouseClicked(event -> {
            if (mediaType.equals("movie")) {
                InfoPageRequestEvent infoPageRequestEvent = new InfoPageRequestEvent(InfoPageRequestEvent.MOVIE_PAGE_REQUEST, id);
                basePane.fireEvent(infoPageRequestEvent);
            } else if (mediaType.equals("tv")) {
                InfoPageRequestEvent infoPageRequestEvent = new InfoPageRequestEvent(InfoPageRequestEvent.TV_PAGE_REQUEST, id);
                basePane.fireEvent(infoPageRequestEvent);
            }
        });
    }

}
