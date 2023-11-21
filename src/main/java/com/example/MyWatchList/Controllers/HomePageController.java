package com.example.MyWatchList.Controllers;

import com.example.MyWatchList.APIClasses.ApiConnection;
import com.example.MyWatchList.DataClasses.MovieDetails;
import com.example.MyWatchList.DataClasses.TvDetails;
import com.example.MyWatchList.NodeClasses.ExtendableCard;
import com.example.MyWatchList.NodeClasses.HomePageNode;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController {
    ApiConnection api = new ApiConnection();

    private static final int numVisNode = 5;
    private static final int numVisNodeTv = 5;
    private int currentindex = 0;
    private int currentindexTv = 0;

    Rectangle clip = new Rectangle(numVisNode * 155,300 ); //adjust according to size needed for 5 nodes
    Rectangle clipTV = new Rectangle(numVisNode * 155,300 ); //adjust according to size needed for 5 nodes
    HBox homeHbox;
    HBox extendableCard;
    HBox homeHBoxTV;
    HBox extendableCardTV;
    Button bwordButton;
    Button fwordButton;
    Button fwordButtonTV;
    Button bwordButtonTV;

    HomePageController(HBox homeHbox, HBox extendableCard, Button bButton, Button fButton, Button tvfButton, Button tvbButton, HBox extendableCardTV, HBox homeHBoxTV){
        this.homeHbox = homeHbox;
        this.extendableCard = extendableCard;
        this.bwordButton = bButton;
        this.fwordButton = fButton;
        this.fwordButtonTV = tvfButton;
        this.bwordButtonTV = tvbButton;
        this.homeHBoxTV = homeHBoxTV;
        this.extendableCardTV = extendableCardTV;


        clipInit();
        moveCouroulsesls();
        moveCouroulseslsTV();
    }

    public  void initializeHomeNodes() {
        //Trending Movies Week
        api.getRequestAsync("https://api.themoviedb.org/3/trending/movie/week?language=en-US", jsonResponse -> Platform.runLater(() ->{
            try {
                int i = 0;
                ExtendableCard card = new ExtendableCard();
                MovieDetails[] movies = MovieDetails.fromJson(jsonResponse);
                for (MovieDetails movie : movies) {
                    HomePageNode node = new HomePageNode(
                            movie.getPosterPath(),
                            i,
                            card,
                            movie.getOverview(),
                            movie.getBackdrop_path(),
                            movie.getTitle(),
                            movie.getVote_average(),
                            movie.getVote_count()
                    );
                    homeHbox.getChildren().add(node.getsNode());
                    i++;
                }
                extendableCard.getChildren().add(card.getNode());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
    }

    public void initHomeNodesTV(){
        api.getRequestAsync("https://api.themoviedb.org/3/trending/tv/week?language=en-US", jsonResponseTV -> Platform.runLater(() ->{
            System.out.println(jsonResponseTV);
            try{
                int i = 0;
                ExtendableCard cardTV = new ExtendableCard();
                TvDetails[] tvs = TvDetails.fromJson(jsonResponseTV);
                for(TvDetails tv: tvs){
                    HomePageNode node = new HomePageNode(
                            tv.getPoster_path(),
                            i,
                            cardTV,
                            tv.getOverview(),
                            tv.getBackdrop_path(),
                            tv.getName(),
                            tv.getVote_average(),
                            tv.getVote_count()
                    );
                    homeHBoxTV.getChildren().add(node.getsNode());
                    i++;
                }
                extendableCardTV.getChildren().add(cardTV.getNode());
            } catch (Exception e){
                e.printStackTrace();
            }
        }));

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
