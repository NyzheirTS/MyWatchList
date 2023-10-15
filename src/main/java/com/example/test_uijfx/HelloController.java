package com.example.test_uijfx;

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


public class HelloController implements Initializable {
    @FXML
    public Pane pnlSettings;
    @FXML
    public GridPane pfpGrid;
    @FXML
    public ImageView profilePICS;
    @FXML
    public HBox pfpHBox;
    @FXML
    public Pane pnlHome;
    @FXML
    public Button fwordButton;
    @FXML
    public Button bwordButton;
    @FXML
    public HBox extendableCard;
    @FXML
    private HBox homeHbox;
    @FXML
    private VBox pnItems;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlWatched;
    @FXML
    private Pane pnlMenus;
    public Button confPFP;
    public Button cancelPFP;

    private ImageView lastSelectedPfp;
    private static final int numVisNode = 6;
    private int currentindex = 0;
    PfpDictionary dic = new PfpDictionary();
    DropShadow shadow = new DropShadow();




    //Init Hub ????????????????????????????????????????????????????????????????????????????
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeShowNodes();
        initializeHomeNodes();
        homeInit();
        profilePICS.setImage(new Image("C:\\Users\\eshas\\IdeaProjects\\Test_UI-JFX\\src\\main\\resources\\com\\example\\test_uijfx\\Fake PFP\\0.jpg"));
        dic.Dictionary();
    }

    private void initializeShowNodes(){
        for ( int i = 0; i<10; ++i){
            WatchedShowsNode node = new WatchedShowsNode(String.format("%.2f", 10 * 100 * Math.random()),i);
            node.bindWidth(pnItems);
            pnItems.getChildren().add(node.getNode());
        }
    }

    private void initializeHomeNodes(){
        ExtendableCard card = new ExtendableCard();
        for (int i = 0; i < 24; ++i){
            HomePageNode node = new HomePageNode(String.format(String.valueOf(i)),i, card);
            homeHbox.getChildren().add(node.getsNode());
        }
        extendableCard.getChildren().add(card.getNode());
    }




    //SHow List NODe Actions control ?????????????????????????????????????????????
    @FXML
    public void newItemNode(ActionEvent event) {
        addNewItem();
    }

    private void addNewItem(){
        int index = pnItems.getChildren().size();
        WatchedShowsNode newNode = new WatchedShowsNode(String.format("%.2f", 10 * 100 * Math.random()),index);
        newNode.bindWidth(pnItems);
        pnItems.getChildren().add(newNode.getNode());
    }


    //HomePage Nodes Actions Control ??????????????????????????????????????????????
    Rectangle clip = new Rectangle(numVisNode * 124,2000); //adjust according to size needed for 6 nodes
    private void homeInit(){
        homeHbox.setClip(clip);
        bwordButton.setVisible(false);
    }
    private void updateClipping(HBox courbox){
        double clipX = currentindex * 125;
        double clipZ = -currentindex * 125;
        ((Rectangle) courbox.getClip()).setX(clipX);
        courbox.setTranslateX(clipZ);
    }

    private void buttonVis(){
        bwordButton.setVisible(currentindex != 0);
        fwordButton.setVisible(currentindex != 24 - numVisNode);
    }

    public void moveCouroulsesls(ActionEvent event) {
        if(event.getSource() == fwordButton) {
            currentindex = Math.min(homeHbox.getChildren().size() - numVisNode, currentindex + numVisNode);
            updateClipping(homeHbox);
            buttonVis();
            System.out.println(homeHbox.getTranslateX());
            System.out.println(currentindex);
        }
        if(event.getSource() == bwordButton){
            currentindex = Math.max(0, currentindex - numVisNode);
            updateClipping(homeHbox);
            buttonVis();
            System.out.println(homeHbox.getTranslateX());
            System.out.println(currentindex);
        }
    }


    //Main Movement System.???????????????????????????????????????????????????
    public void handleClicks(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == btnCustomers) {
            pnlCustomer.setStyle("-fx-background-color : #1620A1");
            pnlCustomer.toFront();
        }
        if (actionEvent.getSource() == btnMenus) {
            pnlMenus.setStyle("-fx-background-color : #000000");
            pnlHome.toFront();
        }
        if (actionEvent.getSource() == btnOverview) {
            pnlWatched.toFront();
        }
        if(actionEvent.getSource()==btnOrders) {
            pnlOrders.setStyle("-fx-background-color : #464F67");
            pnlOrders.toFront();
        }
        if(actionEvent.getSource() == btnSettings){
            pnlSettings.toFront();
        }
    }


    //PFP Actions ??????????????????????????????????????????????????????????
    private void shade() {
        shadow.setRadius(5);
        shadow.setColor(Color.CRIMSON);
    }
    public void getPFP(MouseEvent mouseEvent) {
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