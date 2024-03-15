package com.example.MyWatchList.HomePage;

import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Scale;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class PosterNode  {
    private final Node node;
    private final ImageView image;
    private final int nodeNumber;
    private final ProgressBar bar;
    private final String text;
    private final String mediaType;
    private final String title;
    private static final String baseImgURL = "https://image.tmdb.org/t/p/w780";




    public PosterNode(String text, int nodeNumber, Double score,String title, String mediaType){
    try {
        node = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("carousel-poster-template.fxml")));

        bar = (ProgressBar) node.lookup("#scoreBar");
        image = (ImageView) node.lookup("#imgViewrPics");

        this.nodeNumber = nodeNumber;
        this.text = text;
        this.title = title;
        this.mediaType = mediaType;

        node.getTransforms().add(nodeGrowEvents());
        node.setEffect(dropShadowEffects());

        nodeClickEvent();
        tooltipSettings();
        progressbar(score/10);

    } catch(IOException e){
        throw new RuntimeException(Arrays.toString(e.getStackTrace()));
    }
}


    public Node getNode() {return node;}

    private Scale nodeGrowEvents(){
        Scale scale = new Scale(1, 1);

        ScaleTransition growTransition = new ScaleTransition(Duration.millis(200),node);
        growTransition.setToX(1.08);
        growTransition.setToY(1.08);

        ScaleTransition shrinkTransition = new ScaleTransition(Duration.millis(200),node);
        shrinkTransition.setToY(1);
        shrinkTransition.setToX(1);

        node.setOnMouseEntered(e -> growTransition.play());
        node.setOnMouseExited(e -> shrinkTransition.play());
        return scale;
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

    final DropShadow dropShadowEffects(){
        DropShadow shadow = new DropShadow();
        //shadow.setRadius(10);
        //shadow.setSpread(1000);
        //shadow.setBlurType(BlurType.GAUSSIAN);
        //shadow.setColor(Color.BLACK);
        return shadow;
    }

    final void tooltipSettings(){
       Tooltip tip = new Tooltip(title);
       tip.setHideDelay(Duration.millis(0));
       tip.setShowDelay(Duration.millis(500));
       tip.setFont(Font.font("Verdana", FontPosture.ITALIC, 15 ));
       tip.setTextAlignment(TextAlignment.CENTER);
       tip.setAutoHide(true);
       Tooltip.install(node, tip);
    }

}
