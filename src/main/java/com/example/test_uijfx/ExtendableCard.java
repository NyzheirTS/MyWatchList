package com.example.test_uijfx;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class ExtendableCard {
    private final AnchorPane node;
    private Label label;
    private Rectangle clipRect;

    public ExtendableCard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("extendableCard.fxml"));
            node = loader.load();
            label = (Label) node.lookup("#cardText");

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

    public void setText(String text) {
        label.setText("Info For Node: " + text);
    }

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





