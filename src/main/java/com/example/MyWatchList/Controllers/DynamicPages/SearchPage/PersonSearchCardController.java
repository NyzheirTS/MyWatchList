package com.example.MyWatchList.Controllers.DynamicPages.SearchPage;

import com.example.MyWatchList.Controllers.EventHandlers.PersonPageRequestEvent;
import com.example.MyWatchList.DataModels.SearchModels.SearchModel;
import com.example.MyWatchList.DataModels.Utils.UrlBuilder;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class PersonSearchCardController {
    @FXML private BorderPane basePane;
    @FXML private ImageView posterImg;
    @FXML private Label nameLabel;
    @FXML private Label knownForLabel;
    @FXML private Label knownForLabel1;
    @FXML private Label knownForLabel2;

    private int id;

    public void createCard(String imgURL, String name, SearchModel.Results.KnownFor[] knownFor, int id){
        this.id = id;
        setPosterImg(imgURL);
        setKnownForLabel(knownFor);
        setNameLabel(name);
        setOnAction();
    }

    private void setPosterImg(String imgURL){
        posterImg.setImage(null);
        Task<Object> imageLoadingTask = new Task<>() {
            @Override
            protected Void call() {
                Image loadedImage = new Image(UrlBuilder.getBasePosterw92(imgURL), true);
                Platform.runLater(() -> posterImg.setImage(loadedImage));
                return null;
            }
        };
        new Thread(imageLoadingTask).start();
    }

    private void setKnownForLabel(SearchModel.Results.KnownFor[] knownFor){
        StringBuilder builder = new StringBuilder();
        for (SearchModel.Results.KnownFor known : knownFor){
            builder.append(known.getName());
        }
        knownForLabel.setText(builder.toString());
    }

    private void setNameLabel(String name){
        nameLabel.setText(name);
    }

    private void setOnAction(){
        basePane.setOnMouseClicked(event -> {
            PersonPageRequestEvent requestEvent = new PersonPageRequestEvent(PersonPageRequestEvent.ACTOR_ACTRESS_PAGE_REQUEST, id);
            basePane.fireEvent(requestEvent);
            requestEvent.consume();
        });
    }
}
