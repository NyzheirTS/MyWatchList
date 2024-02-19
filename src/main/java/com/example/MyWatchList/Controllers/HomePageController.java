package com.example.MyWatchList.Controllers;

import com.example.MyWatchList.APIClasses.ApiConnection;
import com.example.MyWatchList.DataClasses.MovieDetails;
import com.example.MyWatchList.DataClasses.TvDetails;
import com.example.MyWatchList.NodeClasses.HomePageNode;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class HomePageController {

    //TODO: Removed the extendablecard want to move it to a dedicated page also change the way the carousel loads instead of all at once load as needed elements ass needed.
    //TODO: build the poster nodes and add them to an array then load them into the carousel as needed.

    private static final int numVisNode = 5;
    private static final int numVisNodeTv = 5;
    private int currentindex = 0;
    private int currentindexTv = 0;

    Rectangle clip = new Rectangle(numVisNode * 155,300 ); //adjust according to size needed for 5 nodes
    Rectangle clipTV = new Rectangle(numVisNode * 155,300 ); //adjust according to size needed for 5 nodes
    HBox homeHbox;
    HBox homeHBoxTV;
    Button bwordButton;
    Button fwordButton;
    Button fwordButtonTV;
    Button bwordButtonTV;

    HomePageController(HBox homeHbox, Button bButton, Button fButton, Button tvfButton, Button tvbButton, HBox homeHBoxTV){
        this.homeHbox = homeHbox;
        this.bwordButton = bButton;
        this.fwordButton = fButton;
        this.fwordButtonTV = tvfButton;
        this.bwordButtonTV = tvbButton;
        this.homeHBoxTV = homeHBoxTV;
        clipInit();
        moveCouroulsesls();
        moveCouroulseslsTV();
    }


    public  void initializeHomeNodes() {
        //Trending Movies Week
        Platform.runLater(() ->{
            try {
                String jsonResponse = ApiConnection.getResponseData(ApiConnection.ApiCallType.MOVIE_TRENDING_WEEK);
                MovieDetails[] movies = MovieDetails.fromJson(jsonResponse);

                List<Node> nodesToAdd = new ArrayList<>();
                System.out.println(jsonResponse);

                for (MovieDetails movie : movies) {
                    HomePageNode node = new HomePageNode(
                            movie.getPosterPath(),
                            movie.getId(),
                            movie.getOverview(),
                            movie.getBackdrop_path(),
                            movie.getTitle(),
                            movie.getVote_average(),
                            movie.getVote_count()
                    );
                    nodesToAdd.add(node.getsNode());
                }
                Platform.runLater(() -> homeHbox.getChildren().addAll(nodesToAdd));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void initHomeNodesTV(){
        //Trending TV Week
        Platform.runLater(() ->{
            try{
                String jsonResponseTV = ApiConnection.getResponseData(ApiConnection.ApiCallType.TV_TRENDING_WEEK);
                TvDetails[] tvs = TvDetails.fromJson(jsonResponseTV);

                List<Node> tvnodesToAdd = new ArrayList<>();

                for(TvDetails tv: tvs){
                    HomePageNode node = new HomePageNode(
                            tv.getPoster_path(),
                            tv.getId(),
                            tv.getOverview(),
                            tv.getBackdrop_path(),
                            tv.getName(),
                            tv.getVote_average(),
                            tv.getVote_count()
                    );
                    tvnodesToAdd.add(node.getsNode());
                }
                Platform.runLater(() -> homeHBoxTV.getChildren().addAll(tvnodesToAdd));
            } catch (Exception e){
                e.printStackTrace();
            }
        });

    }

    private void clipInit() {
        homeHbox.setClip(clip);
        homeHBoxTV.setClip(clipTV);
        bwordButton.setVisible(false);
        bwordButtonTV.setVisible(false);

    }


    // Movie Control
    private void updateClipping(HBox courbox){
        double clipX = currentindex * 155;
        double clipZ = -currentindex * 155;
        ((Rectangle) courbox.getClip()).setX(clipX);
        courbox.setTranslateX(clipZ);
    }

    private void buttonVis(){
        bwordButton.setVisible(currentindex != 0);
        fwordButton.setVisible(currentindex != 20 - numVisNode);
    }

    public void moveCouroulsesls() {
        fwordButton.setOnMouseClicked(event -> {
            currentindex = Math.min(homeHbox.getChildren().size() - numVisNode, currentindex + numVisNode);
            updateClipping(homeHbox);
            buttonVis();
            System.out.println(homeHbox.getTranslateX());
            System.out.println(currentindex);
        });

        bwordButton.setOnMouseClicked(event -> {
            currentindex = Math.max(0, currentindex - numVisNode);
            updateClipping(homeHbox);
            buttonVis();
            System.out.println(homeHbox.getTranslateX());
            System.out.println(currentindex);
        });
    }

    //Tv Control
    private void buttonVisTV(){
        bwordButtonTV.setVisible(currentindexTv != 0);
        fwordButtonTV.setVisible(currentindexTv != 20 - numVisNodeTv);
    }

    public void moveCouroulseslsTV() {
        fwordButtonTV.setOnMouseClicked(event -> {
            currentindexTv = Math.min(homeHBoxTV.getChildren().size() - numVisNodeTv, currentindexTv + numVisNodeTv);
            updateClippingTv(homeHBoxTV);
            buttonVisTV();
            System.out.println(homeHBoxTV.getTranslateX());
            System.out.println(currentindexTv);
        });

        bwordButtonTV.setOnMouseClicked(event -> {
            currentindexTv = Math.max(0, currentindexTv - numVisNodeTv);
            updateClippingTv(homeHBoxTV);
            buttonVisTV();
            System.out.println(homeHBoxTV.getTranslateX());
            System.out.println(currentindexTv);
        });
    }

    private void updateClippingTv(HBox courbox){
        double clipX = currentindexTv * 155;
        double clipZ = -currentindexTv * 155;
        ((Rectangle) courbox.getClip()).setX(clipX);
        courbox.setTranslateX(clipZ);
    }
}
