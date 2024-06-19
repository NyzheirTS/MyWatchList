package com.example.MyWatchList.Controllers.DynamicPages.PersonPage;

import com.example.MyWatchList.DataModels.PersonModels.PersonModel;
import com.example.MyWatchList.TestFolder.TestJsonStringHolder;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

public class PersonPageController {

    @FXML private BorderPane actorActressBase;
    @FXML private ScrollPane leftScrollPane;
    @FXML private ScrollPane mainScrollPane;
    @FXML private HBox filterHBox;


    private static PersonPageController instance;
    private PersonModel model;
    private final HBox header = PersonPageFactory.createActorActressHeader();
    private final VBox leftPanel = PersonPageFactory.createPersonLeftPanel();
    private final BorderPane middlePanel = PersonPageFactory.createPersonMiddlePanel();


    public void update(int ID) throws IOException {
        model = PersonModel.fromJson(TestJsonStringHolder.getJsonStringActorActress());
        updatePage(model);
    }



    private void updatePage(PersonModel model){
        Platform.runLater(() -> {
            PersonHeaderController.getInstance().update(model);
            PersonLeftPanelController.getInstance().update(model);
            PersonMiddleController.getInstance().update(model.getCombined_credits());
        });
    }

    public void initPage(){
        actorActressBase.setTop(header);
        leftScrollPane.setContent(leftPanel);
        actorActressBase.setCenter(middlePanel);
    }



    public static PersonPageController getInstance() {return PersonPageController.instance;}
    public static void setInstance(PersonPageController instance) {
        PersonPageController.instance = instance;}
}
