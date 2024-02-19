package com.example.MyWatchList.Controllers;

import com.example.MyWatchList.APIClasses.ApiConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {
    @FXML
    public AnchorPane pnlSettings;
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
    public ScrollPane pnlHome;
    @FXML
    public StackPane StackPaneCont;
    @FXML
    public Pane homeContentPane;
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

    ApiConnection api = new ApiConnection();

    //Init Hub ????????????????????????????????????????????????????????????????????????????
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        api.fetchData(this::setMethods);
    }

    private void setMethods(){
        HomePageController homePageController = new HomePageController(homeHbox, bwordButton, fwordButton, fwordButtonTV, bwordButtonTV, homeHBoxTV);
        WatchedListController watchedListController = new WatchedListController(pnItems, addNodeButton);

        homePageController.initializeHomeNodes();
        homePageController.initHomeNodesTV();
        watchedListController.initializeShowNodes();

        homeContentPane.prefWidthProperty().bind(pnlHome.widthProperty());
        homeContentPane.prefHeightProperty().bind(pnlHome.heightProperty());
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



}