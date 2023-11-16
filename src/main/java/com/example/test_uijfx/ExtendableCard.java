package com.example.test_uijfx;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Arrays;

public class ExtendableCard {
    private final AnchorPane node;
    private TextArea description;
    private Label titleLabel;
    private ProgressBar scoreBar;
    private Label scoreLabel;
    private ImageView image;
    private Rectangle clipRect;
    private Tooltip tooltip;



    public ExtendableCard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("extendableCard.fxml"));
            node = loader.load();

            //FX Node elements ..
            description = (TextArea) node.lookup("#cardText");
            image = (ImageView) node.lookup("#cardBackgroundImg");
            titleLabel = (Label) node.lookup("#cardTitleLabel");
            scoreBar = (ProgressBar) node.lookup("#cardMovieRatingBar");
            scoreLabel = (Label) node.lookup("#cardScoreLabel");

            // Create a clip rectangle and set it as the clip for the card
            clipRect = new Rectangle();
            clipRect.widthProperty().bind(node.widthProperty());
            clipRect.setHeight(0);  // Initialize the clip height to 0
            node.setClip(clipRect);
        } catch (IOException e) {
            throw new RuntimeException(Arrays.toString(e.getStackTrace()));
        }
    }

    public AnchorPane getNode() {
        return node;
    }

    public void setBackgroundColor(String color) {
        node.setStyle("-fx-background-color: " + color);
    }

    public void setBackGroundImage(String txt) throws IOException {
        image.setImage(ImageCaching.getImage(txt));
        //image.setImage(new Image(txt));
    }

    public void setText(String text) {
        description.setText("Overview: \n" + text);
    }

    public void setScore(Double score, int Votes){
        scoreLabel.setText(Math.round(score * 10)+"%");

        tooltip = new Tooltip("Votes: " + Votes);
        tooltip.setShowDelay(Duration.millis(1));
        tooltip.setHideDelay(Duration.millis(1));
        scoreBar.setProgress(score/10);
        scoreBar.setTooltip(tooltip);
    }

    public void setLabel(String text){titleLabel.setText(text);}

    public void cardGrow() {
        // Get the final height of the card based on its preferred height
        double finalHeight = node.getPrefHeight();

        // Create a Timeline to gradually increase the clip's height to reveal the card
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(clipRect.heightProperty(),0)),
                new KeyFrame(Duration.seconds(.2), new KeyValue(clipRect.heightProperty(), finalHeight))
        );

        // Play the animation
        timeline.play();
    }

    public void cardShrink(){

        double startingHeight = node.getPrefHeight();

        Timeline shrinking = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(clipRect.heightProperty(),startingHeight)),
                new KeyFrame(Duration.seconds(.2), new KeyValue(clipRect.heightProperty(),0))
        );
        shrinking.play();
    }
}





