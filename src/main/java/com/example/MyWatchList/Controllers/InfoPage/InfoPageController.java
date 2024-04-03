package com.example.MyWatchList.Controllers.InfoPage;

import com.example.MyWatchList.DataModels.CommonModels.MediaInfoPageModel;
import com.example.MyWatchList.DataModels.CommonModels.MediaInfoPageModelFactory;
import com.example.MyWatchList.DataModels.MovieModels.MovieInfoPageModel;
import com.example.MyWatchList.DataModels.TvModels.TvInfoPageModel;
import com.example.MyWatchList.TestFolder.TestJsonStringHolder;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

import java.io.IOException;

public class InfoPageController {

    @FXML private ScrollPane rightPanel;
    @FXML private ScrollPane middleContainer;
    @FXML private ScrollPane leftPanelContainer;
    @FXML private BorderPane InfopageHome;
    @FXML private ScrollPane footerContainer;

    private int mediaID;
    private String mediaType;

    //TODO: Work on middle panel and reviews object


    public void initInfoPage(int MediaID, String MediaType) throws IOException {
        this.mediaType = MediaType;
        this.mediaID = MediaID;
        MediaInfoPageModel infoPageModel = getJsonTestString(mediaType);
        if (infoPageModel instanceof MovieInfoPageModel){
            buildMoviePage((MovieInfoPageModel) infoPageModel);
        } else if (infoPageModel instanceof TvInfoPageModel) {
            System.out.println("To Be Implemented");
        } else {
            throw new IllegalStateException("Unsupported Media Type");
        }
    }


    private void buildMoviePage(MovieInfoPageModel infoPageModels){
        footerContainer.setContent(InfoPageFactory.createFooter(infoPageModels));
        rightPanel.setContent(InfoPageFactory.createRightPanel(infoPageModels, mediaType));
        leftPanelContainer.setContent(InfoPageFactory.createMovieLeftPanel(infoPageModels));
        InfopageHome.setTop(InfoPageFactory.createMovieHeader(infoPageModels));
        middleContainer.setContent(InfoPageFactory.createMiddlePanel(infoPageModels));
    }

    private void buildTvPage(TvInfoPageModel infoPageModel){

    }

    public MediaInfoPageModel getJsonTestString(String mediaType) throws IOException {
        if (mediaType.equals("movie")){
            return MediaInfoPageModelFactory.fromJson(TestJsonStringHolder.getJsonStringMovie(),mediaType);
        } else if (mediaType.equals("tv")){
            return MediaInfoPageModelFactory.fromJson(TestJsonStringHolder.getJsonStringTV(), mediaType);
        }
        return null;
    }




}
