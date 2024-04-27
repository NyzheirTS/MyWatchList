package com.example.MyWatchList.Controllers.WatchedList;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class WatchedListFactory {
    private WatchedListFactory(){}

    public static HBox createListItem(String labelText, int nodeNumber){
        try {
            FXMLLoader loader = new FXMLLoader(WatchedListFactory.class.getResource("/com/example/MyWatchList/Controllers/WatchedList/list-item-template.fxml"));
            HBox listItem = loader.load();

            WatchListNodeController controller = loader.getController();
            controller.initWatchListNode(labelText, nodeNumber);

            listItem.getProperties().put("listItemController", controller);

            return listItem;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static VBox createWatchedList(){
        try{
            FXMLLoader loader = new FXMLLoader(WatchedListFactory.class.getResource("/com/example/MyWatchList/Controllers/WatchedList/watched-list.fxml"));
            VBox watchedlist = loader.load();

            WatchedListController controller = loader.getController();
            controller.initWatchedList();

            watchedlist.getProperties().put("watchedListController", controller);

            return watchedlist;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

}
