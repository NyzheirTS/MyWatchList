package com.example.MyWatchList.Controllers.DynamicPages.MovieInfoPages;

import com.example.MyWatchList.Controllers.DynamicPages.*;
import com.example.MyWatchList.Controllers.EventHandlers.CastCrewRequestEvent;
import com.example.MyWatchList.DataModels.CommonModels.*;
import com.example.MyWatchList.DataModels.MovieModels.MovieInfoPageModel;
import com.example.MyWatchList.DataModels.TvModels.TvInfoPageModel;
import com.example.MyWatchList.TestFolder.TestJsonStringHolder;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MovieInfoPageController {

    @FXML private BorderPane infopage;
    @FXML private Hyperlink linkToCastCrewPage;
    @FXML private ScrollPane rightPanelContainer;
    @FXML private ScrollPane middleContainer;
    @FXML private ScrollPane leftPanelContainer;
    @FXML private ScrollPane footerContainer;


    //TODO: Implement the Api Call

    private static MovieInfoPageController instance;
    private int nodeID;
    private String mediaType;
    private static final String ComponentController = "controller";
    private final VBox rightPanel = InfoPageFactory.createRightPanel();
    private final HBox header = InfoPageFactory.createHeader();
    private final VBox leftPanel = InfoPageFactory.createLeftPanel();
    private final HBox footer = InfoPageFactory.createFooter();
    private final VBox middle = InfoPageFactory.createMiddlePanel();
    private MovieInfoPageModel model;

    public void update(int nodeID, String mediaType) throws IOException {
        this.nodeID = nodeID;
        this.mediaType = mediaType;
        model = MediaInfoPageModelDeserializer.fromJson(TestJsonStringHolder.getJsonStringMovie(), MovieInfoPageModel.class); // when update set to model so the on init methods can use the current string
        buildMoviePage(model);
    }

    public void initMethods(){
        setHyperLink();
    }


    private void buildMoviePage(MovieInfoPageModel jsonString){
        setRightPanelContainer(jsonString.getRecommendations());
        setHeader(jsonString);
        setLeftPanel(jsonString);
        setFooter(jsonString.getCredits());
        setMiddlePanel(jsonString);
    }

    private void setRightPanelContainer(RecommendationsModel string){
        if (rightPanel != null && rightPanel.getProperties().containsKey(ComponentController)) {
            RightPanelController rightPanelController = (RightPanelController) rightPanel.getProperties().get(ComponentController);
            rightPanelController.updateRightPanel(string, mediaType);
            rightPanelContainer.setContent(rightPanel);
        }
    }

    private void setHeader(MovieInfoPageModel string){
        if (header != null && header.getProperties().containsKey(ComponentController)) {
            HeaderController headerController = (HeaderController) header.getProperties().get(ComponentController);
            headerController.updateHeader(string);
            infopage.setTop(header);
        }
    }

    private void setLeftPanel(MovieInfoPageModel string){
        if (leftPanel != null && leftPanel.getProperties().containsKey(ComponentController)) {
            LeftPanelController leftPanelController = (LeftPanelController) leftPanel.getProperties().get(ComponentController);
            leftPanelController.updateLeftPanel(string);
            leftPanelContainer.setContent(leftPanel);
        }
    }

    private void setFooter(CreditsModel string){
        if(footer != null && footer.getProperties().containsKey(ComponentController)){
            FooterController footerController = (FooterController) footer.getProperties().get(ComponentController);
            footerController.updateFooter(string);
            footerContainer.setContent(footer);
        }
    }

    private void setMiddlePanel(MovieInfoPageModel string){
        if (middle != null && middle.getProperties().containsKey(ComponentController)){
            MiddlePanelController middleController = (MiddlePanelController) middle.getProperties().get(ComponentController);
            middleController.updateMiddle(string);
            middleContainer.setContent(middle);
        }
    }

    private void setHyperLink(){
        linkToCastCrewPage.setOnMouseClicked(event -> {
            CastCrewRequestEvent infoPageRequestEvent = new CastCrewRequestEvent(CastCrewRequestEvent.CAST_CREW_PAGE_REQUEST, model.getCredits());
            linkToCastCrewPage.fireEvent(infoPageRequestEvent);
            infoPageRequestEvent.consume();
        });
    }

    public static MovieInfoPageController getInstance(){
        return MovieInfoPageController.instance;
    }

    public static void setInstance(MovieInfoPageController instance){
        MovieInfoPageController.instance = instance;
    }
}
