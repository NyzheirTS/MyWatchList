package com.example.MyWatchList.Controllers.InfoPage.MovieInfoPage;

import com.example.MyWatchList.AppConfig.AppCleaner;
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

import java.lang.ref.Cleaner;

public class MovieHeaderController implements AppCleaner {

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
    private MovieInfoPageModel jsonString;

    public void initMovieHeader(MovieInfoPageModel jsonString){
        this.jsonString = jsonString;
        setBackgroundImage();
        setLeftHeader();
        setMiddleHeader();
        setRightHeader();
    }

    private void setBackgroundImage(){
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

    private void setLeftHeader(){
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

    private void setMiddleHeader(){
        taglineLabel.setText(String.format("\"%s\"",jsonString.getTagline()));
        titleLabel.setText(jsonString.getTitle());
        genresLabel.setText(String.format("(%s, %s)",jsonString.getGenres()[0].getName(),jsonString.getGenres()[1].getName()));
        descriptionTextfield.setText(jsonString.getOverview());
    }

    private void setRightHeader(){
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

    public void unloadImg(){
        this.backgroundImage.setImage(null);
        this.qrCodeImageView.setImage(null);
        this.staticPosterImage.setImage(null);
    }


    public void nullText(){
        this.votesLabel.setText(null);
        this.percentRatingLabel.setText(null);
        this.descriptionTextfield.setText(null);
        this.genresLabel.setText(null);
        this.titleLabel.setText(null);
        this.taglineLabel.setText(null);
    }

    @Override
    public void cleanup() {
        unloadImg();
        nullText();
        jsonString = null;
        //System.out.println("MovieHeader Cleaned");
    }
}
