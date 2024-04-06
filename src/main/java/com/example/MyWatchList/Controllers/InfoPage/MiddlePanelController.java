package com.example.MyWatchList.Controllers.InfoPage;

import com.example.MyWatchList.AppConfig.AppCleaner;
import com.example.MyWatchList.Controllers.HomePage.CarouselController;
import com.example.MyWatchList.DataModels.CommonModels.MediaInfoPageModel;
import com.example.MyWatchList.DataModels.CommonModels.ReviewsModel;
import com.example.MyWatchList.DataModels.CommonModels.VideosModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class MiddlePanelController implements AppCleaner {

    @FXML private ScrollPane youtubeContainer;
    @FXML private ScrollPane reviewsContainer;
    private MediaInfoPageModel jsonString;
    private final HBox reviewHBox = new HBox();
    private final HBox thumbnailHbox = new HBox();


    public void initMiddlePanel(MediaInfoPageModel jsonString){
        this.jsonString = jsonString;
        setHboxStyle();
        Platform.runLater(this::setMiddleContainers);

    }

    private void setMiddleContainers(){
        youtubeContainer.setContent(makeThumbnails());
        reviewsContainer.setContent(makeReviews());
    }

    private HBox makeThumbnails(){
        VideosModel.Videos[] videos = jsonString.getVideos().getResults();
        for (VideosModel.Videos video : videos){
            if(video.getType().equals("Trailer") && video.getOfficial()) {
                ImageView node = InfoPageFactory.createEmbedYoutube(video.getKey());
                thumbnailHbox.getChildren().add(node);
            }
        }
        return thumbnailHbox;
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
        thumbnailHbox.setSpacing(10);
        reviewHBox.setPadding(new Insets(5,5,5,5));
        thumbnailHbox.setPadding(new Insets(5,5,5,5));
    }

    private ReviewsController getControllerFromCarousel(HBox node) {
        return (ReviewsController) node.getProperties().get("controller");
    }


    @Override
    public void cleanup() {
        cleanAndClear(reviewHBox);
        cleanAndClearIMGVEW(thumbnailHbox);
        reviewsContainer.setContent(null);
        youtubeContainer.setContent(null);
    }

    private void cleanAndClear(HBox hBox){
        if (hBox != null){
            for (Node child : hBox.getChildren()) {
                if (child instanceof HBox){
                    Object controller = child.getProperties().get("controller");
                    if (controller instanceof AppCleaner){
                        ((AppCleaner)controller).cleanup();
                    }
                }
            }
        }
        hBox.getChildren().clear();
    }
    private void cleanAndClearIMGVEW(HBox hBox){
        if (hBox != null){
            for (Node child : hBox.getChildren()) {
                if (child instanceof ImageView){
                    Object controller = child.getProperties().get("controller");
                    if (controller instanceof AppCleaner){
                        ((AppCleaner)controller).cleanup();
                    }
                }
            }
        }
        hBox.getChildren().clear();
    }

}
