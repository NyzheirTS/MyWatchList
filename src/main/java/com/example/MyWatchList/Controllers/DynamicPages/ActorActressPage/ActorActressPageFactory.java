package com.example.MyWatchList.Controllers.DynamicPages.ActorActressPage;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class ActorActressPageFactory {
    private ActorActressPageFactory(){}

    public static BorderPane createActorActressPage(){
        try{
            FXMLLoader loader = new FXMLLoader(ActorActressPageFactory.class.getResource("/com/example/MyWatchList/ActorActressPage/actor-actress.fxml"));
            BorderPane page = loader.load();
            ActorActressPageController.setInstance(loader.getController());
            ActorActressPageController.getInstance().initPage();
            return page;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static HBox createActorActressHeader(){
        try{
            FXMLLoader loader = new FXMLLoader(ActorActressPageFactory.class.getResource("/com/example/MyWatchList/ActorActressPage/actor-actress-header.fxml"));
            HBox header = loader.load();
            ActorActressHeaderController.setInstance(loader.getController());
            return header;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
