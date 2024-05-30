package com.example.MyWatchList.Controllers.DynamicPages;

import com.example.MyWatchList.Controllers.CommonComponent.QrCodeGen;
import com.example.MyWatchList.DataModels.UrlBuilder;
import com.example.MyWatchList.DataModels.MovieModels.MovieInfoPageModel;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.controlsfx.control.Rating;

public class HeaderController {


    @FXML private Label votesLabel;
    @FXML private Rating showRating;
    @FXML private Label percentRatingLabel;
    @FXML private ImageView qrCodeImageView;
    @FXML private TextArea descriptionTextfield;
    @FXML private Label genresLabel;
    @FXML private Label titleLabel;
    @FXML private Label taglineLabel;
    @FXML private ImageView staticPosterImage;
    @FXML private ImageView backgroundImage;

    private Task<Void> imageLoadingTask;

    public void updateHeader(MovieInfoPageModel jsonString){
        setBackgroundImage(jsonString);
        setLeftHeader(jsonString);
        setMiddleHeader(jsonString);
        setRightHeader(jsonString);
    }

    private void setBackgroundImage(MovieInfoPageModel jsonString){
        backgroundImage.setImage(null);
        imageLoadingTask = new Task<>() {
            @Override
            protected Void call() {
                Image loadedImage = new Image(UrlBuilder.getBackDropImageURL(jsonString.getBackdrop_path()));
                Platform.runLater(() -> backgroundImage.setImage(loadedImage));
                return null;
            }
        };
        new Thread(imageLoadingTask).start();
    }

    private void setLeftHeader(MovieInfoPageModel jsonString){
        staticPosterImage.setImage(null);
        imageLoadingTask = new Task<>() {
            @Override
            protected Void call() {
                Image loadedImage = new Image(UrlBuilder.getBackDropImageURL(jsonString.getPoster_path()));
                Platform.runLater(() -> staticPosterImage.setImage(loadedImage));
                return null;
            }
        };
        new Thread(imageLoadingTask).start();
    }

    private void setMiddleHeader(MovieInfoPageModel jsonString){
        taglineLabel.setText(String.format("\"%s\"",jsonString.getTagline()));
        titleLabel.setText(jsonString.getTitle());
        genresLabel.setText(String.format("(%s, %s)",jsonString.getGenres()[0].getName(),jsonString.getGenres()[1].getName()));
        descriptionTextfield.setText(jsonString.getOverview());
    }

    private void setRightHeader(MovieInfoPageModel jsonString){
        qrCodeImageView.setImage(QrCodeGen.genQrCode(UrlBuilder.getWatchLink(jsonString.getId())));
        percentRatingLabel.setText(formatPercent(jsonString.getVote_average() ) + "%");
        showRating.setRating(formatRating(jsonString.getVote_average()));
        votesLabel.setText(String.format("Votes: %d", jsonString.getVote_count()));
    }

    private String formatPercent(double num){
        num = num * 100;
        String conv = String.valueOf(num);
        return conv.substring(0,2);
    }

    private float formatRating(double num){
        return (float) num/2;
    }
}
