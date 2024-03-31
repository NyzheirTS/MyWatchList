package com.example.MyWatchList.Controllers.InfoPage;

import com.example.MyWatchList.Controllers.CommonComponent.PosterNodeController;
import com.example.MyWatchList.Controllers.InfoPage.MovieInfoPage.MovieLeftPanelController;
import com.example.MyWatchList.DataModels.MovieModels.MovieInfoPageModel;
import com.example.MyWatchList.TestFolder.TestJsonStringHolder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

import java.io.IOException;
import java.util.Objects;

public class InfoPageController {
    @FXML
    private ScrollPane leftPanelContainer;
    @FXML
    private BorderPane InfopageHome;
    @FXML
    private ScrollPane footerContainer;
    private int mediaID;
    private String media_type;

    MovieInfoPageModel infoPageModels = MovieInfoPageModel.fromJson(TestJsonStringHolder.getJsonString());

    public InfoPageController() throws IOException {
        // TODO document why this constructor is empty
    }

    public void initInfoPage(int MediaID, String MediaType){
        this.media_type = MediaType;
        this.mediaID = MediaID;
        setBorderContraints();
        if (media_type.equals("movie")){
            buildMoviepage();
        } else{
            System.out.println("TV not here yet");
        }
    }

    private void buildMoviepage(){
        footerContainer.setContent(InfoPageFactory.createFooter(infoPageModels));
        leftPanelContainer.setContent(InfoPageFactory.createMovieLeftPanel(infoPageModels));
        InfopageHome.setTop(InfoPageFactory.createMovieHeader(infoPageModels));
    }
    //TODO: mess with the layout of the borderpane potentially look for new layout to match what i want
    private void setBorderContraints(){
        //footerContainer.setMaxWidth(300);
        leftPanelContainer.setMaxHeight(1920);
    }


}
