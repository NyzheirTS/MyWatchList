package com.example.MyWatchList.Controllers.CommonComponent;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class LogoImageController {
    @FXML private Button click;
    @FXML private ImageView logoImage;

    public void initObject(String url, URL image ){
        setEvents(url);
        setLogoImage(image);

    }

    private void setLogoImage(URL url){
        Platform.runLater(() -> logoImage.setImage(new Image(url.toExternalForm())));
    }

    private void setEvents(String url){
        click.setCursor(Cursor.HAND);
        click.setOnAction(event -> {
            try{
                Desktop.getDesktop().browse(new URI(url));
            }   catch (URISyntaxException | IOException e){
                throw new RuntimeException(e);
            }
        });
    }
}
