package com.example.MyWatchList.Controllers.CommonComponent;

import com.example.MyWatchList.DataModels.Utils.UrlBuilder;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class YoutubeEmbedController {
    @FXML private ImageView youtubethumbnail;

    public void initEmbedController(String key) {
        getImage(key);
        setEvents(key);
    }

    public void getImage(String ytKey){
        Task<Void> imageLoadingTask = new Task<>() {
            @Override
            protected Void call() {
                Image loadedImage = new Image(UrlBuilder.getYoutubeThumbnail(ytKey));
                Platform.runLater(() -> youtubethumbnail.setImage(loadedImage));
                return null;
            }
        };
        new Thread(imageLoadingTask).start();
    }

    private void setEvents(String ytKey){
        youtubethumbnail.setCursor(Cursor.HAND);
        youtubethumbnail.setOnMouseClicked(event -> {
            try{
                Desktop.getDesktop().browse(new URI(UrlBuilder.getBaseYoutubeWatchLink(ytKey)));
            }   catch (URISyntaxException | IOException e){
                throw new RuntimeException(e);
            }
        });
    }

}
