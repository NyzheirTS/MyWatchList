package com.example.MyWatchList.Controllers.InfoPage;

import com.example.MyWatchList.AppConfig.AppCleaner;
import com.example.MyWatchList.DataModels.CommonModels.MediaInfoPageModel;
import com.example.MyWatchList.DataModels.CommonModels.VideosModel;
import com.example.MyWatchList.DataModels.UrlBuilder;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebView;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class YoutubeEmbedController implements AppCleaner {

    @FXML private ImageView youtubethumbnail;
    private Task<Void> imageLoadingTask;
    String ytKey;
    public void initEmbedController(String ytKey){
        this.ytKey = ytKey;
        youtubethumbnail.setCache(false);
        getImage();
        setEvents();
    }


    public void getImage(){
        imageLoadingTask = new Task<>() {
            @Override
            protected Void call() {
                Image loadedImage = new Image(UrlBuilder.getYoutubeThumbnail(ytKey));
                Platform.runLater(() -> youtubethumbnail.setImage(loadedImage));
                return null;
            }
        };
        new Thread(imageLoadingTask).start();
    }

    public void setEvents(){
        youtubethumbnail.setCursor(Cursor.HAND);
        youtubethumbnail.setOnMouseClicked(event -> {
            try{
                Desktop.getDesktop().browse(new URI(UrlBuilder.getBaseYoutubeWatchLink(ytKey)));
            }   catch (URISyntaxException | IOException e){
                throw new RuntimeException(e);
            }
        });
    }


    @Override
    public void cleanup() {
        youtubethumbnail.setImage(null);
        youtubethumbnail.setOnMouseClicked(null);
        youtubethumbnail.setCursor(null);
        //System.out.println("Youtube Embed Cleaned");
    }
}
