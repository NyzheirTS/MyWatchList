package com.example.MyWatchList.NodeClasses;

import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
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

public class HomePageNode{
    private final Node node;
    private final Label label;
    private final ImageView image;
    private final Scale scale = new Scale(1,1);
    private final DropShadow shadow = new DropShadow();
    private final int nodeNumber;
    private boolean nodeFocus = false;

    String baseImgURL = "https://image.tmdb.org/t/p/w780";




    public HomePageNode(String text, int nodeNumber, String desc, String imgTxt, String title, Double score, int votes){
    try {
        node = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CarouselItemsTemplate.fxml")));

        node.getTransforms().add(scale);
        node.setEffect(shadow);

        label = (Label) node.lookup("#homeLabelPageTestingPheromont");
        //label.setText(text);

        image = (ImageView) node.lookup("#imgViewrPics");
        //Async load image
        Task<Void> imageLoadingThread1 = new  Task<>(){
            @Override
            protected Void call(){
                Image loadedImage = new Image(baseImgURL + text);
                Platform.runLater(() -> image.setImage(loadedImage));
                return null;
            }
        };

        new Thread(imageLoadingThread1).start();

        this.nodeNumber = nodeNumber;

        nodeGrowEvents();
        nodeClickEvent();

    } catch(IOException e){
        throw new RuntimeException(Arrays.toString(e.getStackTrace()));
    }
}
    public Node getsNode() {return node;}

    public void nodeGrowEvents(){
        ScaleTransition growTransition = new ScaleTransition(Duration.millis(200),node);
        growTransition.setToX(1.08);
        growTransition.setToY(1.08);

        ScaleTransition shrinkTransition = new ScaleTransition(Duration.millis(200),node);
        shrinkTransition.setToY(1);
        shrinkTransition.setToX(1);

        node.setOnMouseEntered(e -> {
            if(!nodeFocus) {
                growTransition.play();
            }
        });
        node.setOnMouseExited(e -> {
            if(!nodeFocus) {
                shrinkTransition.play();
            }
        });
    }

    private void nodeClickEvent(){
        node.setOnMouseClicked(event -> {
            System.out.println(String.valueOf(nodeNumber));
        });
    }

    final void dropShadowEffects(){
        shadow.setRadius(10);
        shadow.setSpread(1000);
        shadow.setBlurType(BlurType.GAUSSIAN);
        shadow.setColor(Color.BLACK);
    }

}
