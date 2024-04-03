package com.example.MyWatchList.Controllers.InfoPage;

import com.example.MyWatchList.DataModels.CommonModels.MediaInfoPageModel;
import com.example.MyWatchList.DataModels.CommonModels.VideosModel;
import com.example.MyWatchList.DataModels.UrlBuilder;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebView;

public class YoutubeEmbedController {

    //TODO: fix video still playing after going to different page;

    @FXML private HBox embedContainer;
    private MediaInfoPageModel jsonString;
    private WebView webView;
    private static HostServices hostServices;
    public void initEmbedController(MediaInfoPageModel jsonString){
        this.jsonString = jsonString;
        Platform.runLater(this::buildHBox);
    }


    private void buildHBox(){
        VideosModel.Videos[] videos = jsonString.getVideos().getResults();
        for (VideosModel.Videos video : videos){
            if(video.getType().equals("Trailer") && video.getOfficial()) {
                webView = new WebView();
                webView.getEngine().load(UrlBuilder.getYoutubeLink(video.getKey()));
                webView.setPrefSize(400, 250);
                webView.cacheProperty().set(true);
                setAfter(webView);
                embedContainer.getChildren().add(webView);
            }
        }
    }

    public void setVisability(){
    }

    private void setAfter(WebView webView){
        final String ogUrl = webView.getEngine().getLocation();
        webView.getEngine().locationProperty().addListener((observable, oldvalue, newvalue) -> {
            if (newvalue.contains("https://www.youtube.com/watch")){
                try{
                    hostServices.showDocument(newvalue);
                    Platform.runLater(() -> webView.getEngine().load(ogUrl));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Platform.runLater(() -> webView.getEngine().load(ogUrl));
            }
        });
    }

    public static void setHostServices(HostServices hostServices){
        YoutubeEmbedController.hostServices = hostServices;
    }
}
