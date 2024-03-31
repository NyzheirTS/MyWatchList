package com.example.MyWatchList.Controllers.CommonComponent;

import com.example.MyWatchList.Controllers.InfoPage.InfoPageFactory;
import com.example.MyWatchList.Controllers.UrlBuilder;
import com.example.MyWatchList.Controllers.InfoPage.InfoPageRequestEvent;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Scale;
import javafx.util.Duration;


public class PosterNodeController {
    @FXML
    private AnchorPane posterPane;
    @FXML
    private ImageView posterImageView;
    @FXML
    private ProgressBar scoreBar;


    private int nodeNumber;
    private String mediaType;
    private String imgID;
    private String title;
   // private static final String baseImgURL = "https://image.tmdb.org/t/p/w780";




    public void initPosterNode(String imgID, int nodeNumber, Double score, String title, String mediaType){
        this.nodeNumber = nodeNumber;
        this.mediaType = mediaType;
        this.imgID = imgID;
        this.title = title;



        nodeClickEvent();
        nodeGrowEvents();
        tooltipSettings();
        posterPane.setEffect(dropShadowEffects());
        scoreBar.setProgress(score/10);

}




    private Scale nodeGrowEvents(){
        Scale scale = new Scale(1, 1);

        ScaleTransition growTransition = new ScaleTransition(Duration.millis(200),posterPane);
        growTransition.setToX(1.08);
        growTransition.setToY(1.08);

        ScaleTransition shrinkTransition = new ScaleTransition(Duration.millis(200),posterPane);
        shrinkTransition.setToY(1);
        shrinkTransition.setToX(1);

        posterPane.setOnMouseEntered(e -> growTransition.play());
        posterPane.setOnMouseExited(e -> shrinkTransition.play());
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
                Image loadedImage = new Image(UrlBuilder.getPosterImageURL(imgID)); //true to enable Background loading
                Platform.runLater(() -> posterImageView.setImage(loadedImage));
                return null;
            }
        };

        new Thread(imageLoadingTask).start();
    }

    public void unloadImg(){
        posterImageView.setImage(null);
    }

    private void nodeClickEvent(){
        posterPane.setOnMouseClicked(event -> {
            InfoPageRequestEvent infoPageRequestEvent = new InfoPageRequestEvent(InfoPageFactory.createInfoPage(nodeNumber, mediaType));
            System.out.println(nodeNumber);
            System.out.println(mediaType);
            posterPane.fireEvent(infoPageRequestEvent);
        });
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
       Tooltip.install(posterPane, tip);
    }

}
