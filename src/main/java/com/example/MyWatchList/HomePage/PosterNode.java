package com.example.MyWatchList.HomePage;

import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.transform.Scale;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class PosterNode  {
    private final Node node;
    private final ImageView image;
    private final DropShadow shadow = new DropShadow();
    private final int nodeNumber;
    private final ProgressBar bar;
    private final String text;
    private final String mediaType;
    private static final String baseImgURL = "https://image.tmdb.org/t/p/w780";




    public PosterNode(String text, int nodeNumber, Double score, int votes, String mediaType){
    try {
        node = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("carousel-poster-template.fxml")));

        Scale scale = new Scale(1, 1);
        node.getTransforms().add(scale);
        node.setEffect(shadow);

        bar = (ProgressBar) node.lookup("#scoreBar");
        image = (ImageView) node.lookup("#imgViewrPics");

        this.nodeNumber = nodeNumber;
        this.text = text;
        this.mediaType = mediaType;

        nodeGrowEvents();
        nodeClickEvent();
        progressbar(score/10);

    } catch(IOException e){
        throw new RuntimeException(Arrays.toString(e.getStackTrace()));
    }
}


    public Node getNode() {return node;}

    private void nodeGrowEvents(){
        ScaleTransition growTransition = new ScaleTransition(Duration.millis(200),node);
        growTransition.setToX(1.08);
        growTransition.setToY(1.08);

        ScaleTransition shrinkTransition = new ScaleTransition(Duration.millis(200),node);
        shrinkTransition.setToY(1);
        shrinkTransition.setToX(1);

        node.setOnMouseEntered(e -> growTransition.play());
        node.setOnMouseExited(e -> shrinkTransition.play());
    }

    public void loadImg(){
        //Async load image
        /*
        if (image.getImage() != null){
            return;
        }
         */
        Task<Void> imageLoadingTask = new  Task<>(){
            @Override
            protected Void call(){
                Image loadedImage = new Image(baseImgURL + text ); //true to enable Background loading
                Platform.runLater(() -> image.setImage(loadedImage));
                return null;
            }
        };

        new Thread(imageLoadingTask).start();
    }

    public void unloadImg(){
        image.setImage(null);
    }

    private void nodeClickEvent(){
        node.setOnMouseClicked(event -> {
            System.out.println(nodeNumber);
            System.out.println(mediaType);
        });
    }

    private void progressbar(Double score){
        bar.setProgress(score);
    }

    final void dropShadowEffects(){
        shadow.setRadius(10);
        shadow.setSpread(1000);
        shadow.setBlurType(BlurType.GAUSSIAN);
        shadow.setColor(Color.BLACK);
    }

}
