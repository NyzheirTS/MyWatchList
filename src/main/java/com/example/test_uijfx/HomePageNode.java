package com.example.test_uijfx;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.transform.Scale;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class HomePageNode{
    private final Node node;
    private final Label label;
    private final Scale scale = new Scale(1,1);
    private final DropShadow shadow = new DropShadow();
    private final ExtendableCard card;
    private final int nodeNumber;
    private boolean nodeFocus = false;


public HomePageNode(String text, int nodeNumber, ExtendableCard card){
    try {
        node = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HomeItems.fxml")));

        node.getTransforms().add(scale);
        node.setEffect(shadow);

        label = (Label) node.lookup("#homeLabelPageTestingPheromont");
        label.setText(text);

        this.nodeNumber = nodeNumber;
        this.card = card;
        nodeGrowEvents();
        cardEvents();

    } catch(IOException e){
        throw new RuntimeException(Arrays.toString(e.getStackTrace()));
    }
}


    public Node getsNode() {return node;}

    public void cardEvents(){
        node.setOnMouseClicked(event -> {
                card.cardGrow();
                nodeFocus = true;
                card.setText(String.valueOf(nodeNumber));
        });

    }


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

    final void dropShadowEffects(){
        shadow.setRadius(10);
        shadow.setSpread(1000);
        shadow.setBlurType(BlurType.GAUSSIAN);
        shadow.setColor(Color.BLACK);
    }

}
