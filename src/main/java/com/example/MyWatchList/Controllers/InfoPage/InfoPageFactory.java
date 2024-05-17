package com.example.MyWatchList.Controllers.InfoPage;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class InfoPageFactory {

    private static final String ComponentController = "controller";
    private InfoPageFactory(){}

    public static BorderPane createInfoPage() {
        try {
            FXMLLoader loader = new FXMLLoader(InfoPageFactory.class.getResource("/com/example/MyWatchList/InfoPage/info-page.fxml"));
            BorderPane infoPage = loader.load();
            InfoPageController controller = loader.getController();

            infoPage.getProperties().put(ComponentController, controller);  // Store controller in properties
            return infoPage;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static VBox createRightPanel() {
        try {
            FXMLLoader loader = new FXMLLoader(InfoPageFactory.class.getResource("/com/example/MyWatchList/InfoPage/right-panel.fxml"));
            VBox rightPanel = loader.load();
            RightPanelController controller = loader.getController();
            rightPanel.getProperties().put(ComponentController, controller);  // Store controller in properties
            return rightPanel;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static HBox createHeader(){
        try {
            FXMLLoader loader = new FXMLLoader(InfoPageFactory.class.getResource("/com/example/MyWatchList/InfoPage/movie-header.fxml"));
            HBox movieHeader = loader.load();
            HeaderController controller = loader.getController();
            movieHeader.getProperties().put(ComponentController, controller);
            return movieHeader;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static VBox createLeftPanel(){
        try {
            FXMLLoader loader = new FXMLLoader(InfoPageFactory.class.getResource("/com/example/MyWatchList/InfoPage/movie-left-panel.fxml"));
            VBox leftPanel = loader.load();
            LeftPanelController controller = loader.getController();
            leftPanel.getProperties().put(ComponentController, controller);
            return leftPanel;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static HBox createFooter(){
        try {
            FXMLLoader loader = new FXMLLoader(InfoPageFactory.class.getResource("/com/example/MyWatchList/InfoPage/footer.fxml"));
            HBox footer = loader.load();
            FooterController controller = loader.getController();
            footer.getProperties().put(ComponentController, controller);
            return footer;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }




}
