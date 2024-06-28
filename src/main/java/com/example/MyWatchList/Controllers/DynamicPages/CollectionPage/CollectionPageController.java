package com.example.MyWatchList.Controllers.DynamicPages.CollectionPage;

import com.example.MyWatchList.Controllers.DynamicPages.MovieInfoPages.MovieInfoPageController;
import com.example.MyWatchList.DataModels.CollectionModels.CollectionModel;
import com.example.MyWatchList.DataModels.CommonModels.MediaInfoPageModelDeserializer;
import com.example.MyWatchList.DataModels.Utils.UrlBuilder;
import com.example.MyWatchList.TestFolder.TestJsonStringHolder;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.Stack;

public class CollectionPageController {
    private static CollectionPageController instance;
    @FXML private HBox hboxxxx;
    @FXML private Label titleLabel;
    @FXML private Label descriptionLabel;
    @FXML private ScrollPane contentScrollPane;
    @FXML private ImageView backgroundImg;
    CollectionModel model;

    public void update(int collectionID) throws IOException {
        model = CollectionModel.fromJson(TestJsonStringHolder.getJsonStringCollection());
        buildPage();
    }

    private void buildPage(){
        setBackgroundImg();
        setLabels();
        createCards();
    }
    private void setLabels(){
        Platform.runLater(()-> {
            titleLabel.setText(model.getName());
            descriptionLabel.setText(model.getOverview());
        });
    }

    private void setBackgroundImg(){
        backgroundImg.setImage(null);
        Task<Object> imageLoadingTask = new Task<>() {
            @Override
            protected Void call() {
                Image loadedImage = new Image(UrlBuilder.getBackDropImageURL(model.getBackdrop_path()), true);
                Platform.runLater(() -> backgroundImg.setImage(loadedImage));
                return null;
            }
        };
        new Thread(imageLoadingTask).start();
    }

    private void createCards(){
        hboxxxx.getChildren().clear();
        CollectionModel.Parts[] parts = model.getParts();
        for (CollectionModel.Parts part : parts){
            StackPane node = CollectionPageFactory.createCollectionCard(part);
            Platform.runLater(() -> hboxxxx.getChildren().add(node));
        }
    }



    public static CollectionPageController getInstance(){
        return CollectionPageController.instance;
    }

    public static void setInstance(CollectionPageController instance){
        CollectionPageController.instance = instance;
    }
}


