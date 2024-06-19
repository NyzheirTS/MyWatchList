package com.example.MyWatchList.Controllers.CommonComponent;

import com.example.MyWatchList.Controllers.EventHandlers.PersonPageRequestEvent;
import com.example.MyWatchList.DataModels.Utils.UrlBuilder;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class PersonPosterController {
    @FXML private VBox actorPosterItem;
    @FXML private TextArea actorNameArea;
    @FXML private TextArea actorRoleTextArea;
    @FXML private ImageView actorImageView;


    public void initActorPoster(int id, String urlKey, String actorName, String actorRole){
            setMethods(actorName, actorRole);
            loadImg(urlKey);
            setActionEvent(id);
    }

    private void setMethods(String actorName, String actorRole){
        actorNameArea.setText(actorName);
        actorRoleTextArea.setText(actorRole);
    }

    private void setActionEvent(int id){
        actorPosterItem.setCursor(Cursor.HAND);
        actorPosterItem.setOnMouseClicked(event -> {
            PersonPageRequestEvent personPageRequestEvent = new PersonPageRequestEvent(PersonPageRequestEvent.ACTOR_ACTRESS_PAGE_REQUEST, id);
            actorPosterItem.fireEvent(personPageRequestEvent);
        });
    }

    private void loadImg(String urlKey){
        actorImageView.setImage(null);
        Task<Void> imageLoadingTask = new Task<>() {
            @Override
            protected Void call() {
                Image loadedImage = new Image(UrlBuilder.getProfileImageURL(urlKey));
                Platform.runLater(() -> actorImageView.setImage(loadedImage));
                return null;
            }
        };
        new Thread(imageLoadingTask).start();
    }

}
