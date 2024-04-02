package com.example.MyWatchList.Controllers.CommonComponent;

import com.example.MyWatchList.DataModels.UrlBuilder;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ActorPosterController {
    @FXML
    private TextArea actorNameArea;
    @FXML
    private TextArea actorRoleTextArea;
    @FXML
    private ImageView actorImageView;

    private String urlKey;
    private String actorName;
    private String actorRole;

    public void initActorPoster(String urlKey, String actorName, String actorRole){
            this.urlKey = urlKey;
            this.actorName = actorName;
            this.actorRole = actorRole;
            setMethods();
            loadImg();
    }

    private void setMethods(){
        actorNameArea.setText(actorName);
        actorRoleTextArea.setText(actorRole);

    }

    private void loadImg(){
        Task<Void> imageLoadingTask = new  Task<>(){
            @Override
            protected Void call(){
                Image loadedImage = new Image(UrlBuilder.getProfileImageURL(urlKey)); //true to enable Background loading
                Platform.runLater(() -> actorImageView.setImage(loadedImage));
                return null;
            }
        };
        new Thread(imageLoadingTask).start();
    }
}
