package com.example.MyWatchList.Controllers.DynamicPages.ActorActressPage;

import com.example.MyWatchList.DataModels.PersonModels.ActorActressModel;
import com.example.MyWatchList.TestFolder.TestJsonStringHolder;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class ActorActressPageController {

    @FXML private BorderPane actorActressBase;
    @FXML private ScrollPane leftScrollPane;
    @FXML private ScrollPane mainScrollPane;
    @FXML private HBox filterHBox;


    private static ActorActressPageController instance;
    private ActorActressModel model;
    private final HBox header = ActorActressPageFactory.createActorActressHeader();


    public void update(int ID) throws IOException {
        model = ActorActressModel.fromJson(TestJsonStringHolder.getJsonStringActorActress());
        updatePage(model);
    }

    public void initPage(){
        actorActressBase.setTop(header);
    }

    private void updatePage(ActorActressModel model){
        ActorActressHeaderController.getInstance().update(model);
    }



    public static ActorActressPageController getInstance() {return ActorActressPageController.instance;}
    public static void setInstance(ActorActressPageController instance) {ActorActressPageController.instance = instance;}
}
