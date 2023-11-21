package com.example.MyWatchList.Controllers;

import com.example.MyWatchList.DataClasses.MovieDetails;
import com.example.MyWatchList.DataClasses.PfpDictionary;
import com.example.MyWatchList.APIClasses.ApiConnection;
import com.example.MyWatchList.NodeClasses.ExtendableCard;
import com.example.MyWatchList.NodeClasses.HomePageNode;
import com.example.MyWatchList.NodeClasses.WatchedShowsNode;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {
    @FXML
    public AnchorPane pnlSettings;
    @FXML
    public GridPane pfpGrid;
    @FXML
    public ImageView profilePICS;
    @FXML
    public HBox pfpHBox;
    @FXML
    public AnchorPane pnlHome;
    @FXML
    public Button fwordButton;
    @FXML
    public Button bwordButton;
    @FXML
    public HBox extendableCard;
    @FXML
    public Button addNodeButton;
    @FXML
    public HBox extendableCardTV;
    @FXML
    public HBox homeHBoxTV;
    @FXML
    public Button fwordButtonTV;
    @FXML
    public Button bwordButtonTV;
    @FXML
    private HBox homeHbox;
    @FXML
    private VBox pnItems;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnSettings;
    @FXML
    private AnchorPane pnlWatched;
    @FXML
    private Button confPFP;
    @FXML
    private Button cancelPFP;




    //Init Hub ????????????????????????????????????????????????????????????????????????????
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setMethods();
    }


    private void setMethods(){
        HomePageController homePageController = new HomePageController(homeHbox, extendableCard, bwordButton, fwordButton, fwordButtonTV, bwordButtonTV, extendableCardTV, homeHBoxTV);
        WatchedListController watchedListController = new WatchedListController(pnItems, addNodeButton);

        homePageController.initializeHomeNodes();
        homePageController.initHomeNodesTV();
        watchedListController.initializeShowNodes();

        initDic();
    }



    //Main Movement System.???????????????????????????????????????????????????
    public void handleClicks(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == btnOverview) {
            pnlHome.toFront();
        }
        if(actionEvent.getSource()==btnOrders) {
            pnlWatched.toFront();
        }
        if(actionEvent.getSource() == btnSettings){
            pnlSettings.toFront();
        }
    }


    //Settings Page Profile Pic System.
    private ImageView lastSelectedPfp;
    DropShadow shadow = new DropShadow();
    PfpDictionary dic = new PfpDictionary();

    public void shade() {
        shadow.setRadius(5);
        shadow.setColor(Color.CRIMSON);
    }

    public void initDic(){
        dic.Dictionary();
    }


    public void controlPfp(MouseEvent mouse) {
        ImageView img = (ImageView) mouse.getSource();
        lastSelectedPfp = img;


        if (lastSelectedPfp != null){
            lastSelectedPfp.setEffect(shadow);
        }

        for(Node node : pfpGrid.getChildren()){
            if(node instanceof ImageView){
                ((ImageView) node).setEffect(null);
            }
        }

        shade();
        img.setEffect(shadow);

        pfpHBox.setVisible(true);
        confPFP.setOnAction(event -> {
            img.setEffect(shadow);
            profilePICS.setImage(new Image(dic.getPIC(img.getId())));
            pfpHBox.setVisible(false);
        });
        cancelPFP.setOnAction(event -> {
            img.setEffect(null);
            pfpHBox.setVisible(false);
        });

    }


}