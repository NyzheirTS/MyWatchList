package com.example.MyWatchList.Controllers.InfoPage;

import com.example.MyWatchList.Controllers.CommonComponent.CommonFactory;
import com.example.MyWatchList.DataModels.CommonModels.MediaInfoPageModel;
import com.example.MyWatchList.DataModels.CommonModels.ReviewsModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import org.controlsfx.control.PopOver;

public class ReviewsController {
    @FXML private HBox reviewHbox;
    @FXML private Label authorLabel;
    @FXML private Label usernameLabel;
    @FXML private Label ratingLabel;

    private double targetX;
    private double targetY;

     private String authorname;
     private String username;
     private int rating;
     public void initReviews(String authorname, String username, int rating){
         this.authorname = authorname;
         this.username = username;
         this.rating = rating;
         buildReview();
     }

     private void buildReview(){
            authorLabel.setText(authorname);
            usernameLabel.setText(username);
            ratingLabel.setText(String.valueOf(rating));
     }

    public void makePopOver(String content, String createdAt, String updatedAt){
         PopOver pop = new PopOver(InfoPageFactory.createPopOver(content, createdAt, updatedAt, username));
         reviewHbox.setOnMouseClicked(event -> {
             if (!pop.isDetached()){
                 pop.hide();
             }
             if (event.getClickCount() == 2 ){
                 if (pop.isShowing()){
                     pop.hide(Duration.ZERO);
                 }
                 targetX = event.getSceneX();
                 targetY = event.getScreenY();

                 pop.show(reviewHbox, targetX, targetY);
             }
         });
    }
}
