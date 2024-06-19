package com.example.MyWatchList.Controllers.DynamicPages.PersonPage;

import com.example.MyWatchList.Controllers.EventHandlers.InfoPageRequestEvent;
import com.example.MyWatchList.DataModels.Utils.UrlBuilder;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class PersonInfoCardController {

    @FXML private HBox parent;
    @FXML private ImageView posterImg;
    @FXML private Label title;
    @FXML private Label character;
    @FXML private Label date;
    @FXML private Label rating;

    private String datetime;
    private Double rate;
    private int id;
    private String media_type;
    public void createCard(String img, String title, String character, String datetime, Double rate, int id, String media_type){
        this.datetime = datetime;
        this.rate = rate;
        this.id = id;
        this.media_type = media_type;
        setImage(img);
        setTitleChar(character, title);
        setDateRating();
    }

    private void setImage(String img){
        posterImg.setImage(null);
        Task<Object> imageLoadingTask = new Task<>() {
            @Override
            protected Void call() {
                Image loadedImage = new Image(UrlBuilder.getBasePosterw92(img), true);
                Platform.runLater(() -> posterImg.setImage(loadedImage));
                return null;
            }
        };
        new Thread(imageLoadingTask).start();
    }

    private void setTitleChar(String chars, String titles){
        title.setText(titles);
        character.setText(chars);
    }

    private void setDateRating(){
        rating.setText(String.valueOf(rate));
        date.setText(String.valueOf(datetime));
    }

    private void setClickable(){
        parent.setOnMouseClicked(event -> {
            if (media_type.equals("movie")) {
                InfoPageRequestEvent infoPageRequestEvent = new InfoPageRequestEvent(InfoPageRequestEvent.MOVIE_PAGE_REQUEST, id);
                parent.fireEvent(infoPageRequestEvent);
            } else if (media_type.equals("tv")) {
                InfoPageRequestEvent infoPageRequestEvent = new InfoPageRequestEvent(InfoPageRequestEvent.TV_PAGE_REQUEST, id);
                parent.fireEvent(infoPageRequestEvent);
            }
        });
    }


    public String getDatetime() {
        return datetime;
    }

    public Double getRate() {
        return rate;
    }
}
