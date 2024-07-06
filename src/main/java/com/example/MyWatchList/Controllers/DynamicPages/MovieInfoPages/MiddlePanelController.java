package com.example.MyWatchList.Controllers.DynamicPages.MovieInfoPages;

import com.example.MyWatchList.Controllers.CommonComponent.CommonFactory;
import com.example.MyWatchList.Controllers.CommonComponent.ReviewsController;
import com.example.MyWatchList.DataModels.CommonModels.ReviewsModel;
import com.example.MyWatchList.DataModels.CommonModels.VideosModel;
import com.example.MyWatchList.DataModels.MovieModels.MovieInfoPageModel;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class MiddlePanelController {

    @FXML private HBox reviewsContainer;
    @FXML private HBox youtubeContainer;

    public void updateMiddle(MovieInfoPageModel string){
        setYoutubeContainer(string);
        setReviewsContainer(string);
    }

    private void setYoutubeContainer(MovieInfoPageModel key){
        youtubeContainer.getChildren().clear();
        VideosModel.Videos[] videos = key.getVideos().getResults();
        for (VideosModel.Videos video : videos){
            if (video.getType().equals("Trailer") && video.getOfficial()){
                ImageView node = CommonFactory.createEmbedYoutube(video.getKey());
                youtubeContainer.getChildren().add(node);
            }
        }
    }

    private void setReviewsContainer(MovieInfoPageModel string){
        reviewsContainer.getChildren().clear();
        ReviewsModel.Reviews[] reviews = string.getReviews().getResults();
        for (ReviewsModel.Reviews review : reviews){
            HBox node = CommonFactory.createReviews(
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
            reviewsContainer.getChildren().add(node);
        }
    }

    private ReviewsController getControllerFromCarousel(HBox node) {
        return (ReviewsController) node.getProperties().get("controller");
    }

}
