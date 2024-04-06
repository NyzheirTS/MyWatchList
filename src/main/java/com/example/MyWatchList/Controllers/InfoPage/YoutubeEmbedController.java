package com.example.MyWatchList.Controllers.InfoPage;

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

public class YoutubeEmbedController {

    private static HostServices hostServices;
    @FXML private ImageView youtubethumbnail;
    String ytKey;
    public void initEmbedController(String ytKey){
        this.ytKey = ytKey;
        getImage();
        setEvents();
    }


    public void getImage(){
        Task<Void> imageLoadingTask = new  Task<>(){
            @Override
            protected Void call(){
                Image loadedImage = new Image(UrlBuilder.getYoutubeThumbnail(ytKey)); //true to enable Background loading
                Platform.runLater(() -> youtubethumbnail.setImage(loadedImage));
                return null;
            }
        };
        new Thread(imageLoadingTask).start();
    }

    public void setEvents(){
        youtubethumbnail.setCursor(Cursor.HAND);
        youtubethumbnail.setOnMouseClicked(event -> hostServices.showDocument(UrlBuilder.getBaseYoutubeWatchLink(ytKey)));
    }

    public static void setHostServices(HostServices hostServices){
        YoutubeEmbedController.hostServices = hostServices;
    }
}
