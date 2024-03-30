package com.example.MyWatchList.Controllers.InfoPage;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import org.controlsfx.control.Rating;

public class MovieHeaderController {
    @FXML
    private Label votesLabel;
    @FXML
    private Rating showRating;
    @FXML
    private Label percentRatingLabel;
    @FXML
    private ImageView qrCodeImageView;
    @FXML
    private TextArea descriptionTextfield;
    @FXML
    private Label genresLabel;
    @FXML
    private Label titleLabel;
    @FXML
    private Label taglineLabel;
    @FXML
    private ImageView staticPosterImage;
    @FXML
    private ImageView backgroundImage;

    private String jsonString;

    //TODO: Continue to work on this Page Look At the ControlsFx Rating Source Code to change look of it.

    public void initMovieHeader(String jsonString){
        this.jsonString = jsonString;
    }
}
