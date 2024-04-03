package com.example.MyWatchList.Controllers.InfoPage;

import com.example.MyWatchList.Controllers.HomePage.CarouselController;
import com.example.MyWatchList.DataModels.CommonModels.MediaInfoPageModel;
import com.example.MyWatchList.DataModels.CommonModels.ReviewsModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;

public class MiddlePanelController {

    @FXML private ScrollPane youtubeContainer;
    @FXML private ScrollPane reviewsContainer;
    private MediaInfoPageModel jsonString;
    private final HBox reviewHBox = new HBox();


    public void initMiddlePanel(MediaInfoPageModel jsonString){
        this.jsonString = jsonString;
        setHboxStyle();
        Platform.runLater(this::setMiddleContainers);

    }

    private void setMiddleContainers(){
        youtubeContainer.setContent(InfoPageFactory.createEmbedYoutube(jsonString));
        reviewsContainer.setContent(makeReviews());
    }

    private HBox makeReviews(){
        ReviewsModel.Reviews[] reviews = jsonString.getReviews().getResults();
        for (ReviewsModel.Reviews review : reviews){
            HBox node = InfoPageFactory.createReviews(
                    review.getAuthor(),
                    review.getAuthor_details().getUsername(),
                    review.getAuthor_details().getRating()
            );
            assert node != null;
            getControllerFromCarousel(node).makePopOver(
                    review.getContent(),
                    review.getCreated_at(),
                    review.getUpdated_at()
            );
            reviewHBox.getChildren().add(node);
        }
        return reviewHBox;
    }

    private void setHboxStyle(){
        reviewHBox.setSpacing(10);
        reviewHBox.setPadding(new Insets(5,5,5,5));
    }

    private ReviewsController getControllerFromCarousel(HBox node) {
        return (ReviewsController) node.getProperties().get("reviewController");
    }



}
