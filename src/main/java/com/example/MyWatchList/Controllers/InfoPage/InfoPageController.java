package com.example.MyWatchList.Controllers.InfoPage;

import com.example.MyWatchList.AppEntry.Pair;
import com.example.MyWatchList.DataModels.CommonModels.MediaInfoPageModel;
import com.example.MyWatchList.DataModels.CommonModels.MediaInfoPageModelFactory;
import com.example.MyWatchList.DataModels.MovieModels.MovieInfoPageModel;
import com.example.MyWatchList.DataModels.TvModels.TvInfoPageModel;
import com.example.MyWatchList.TestFolder.TestJsonStringHolder;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class InfoPageController {

    @FXML private Button backButton;
    @FXML private BorderPane infopage;
    @FXML private Hyperlink linkToCastCrewPage;
    @FXML private ScrollPane rightPanelContainer;
    @FXML private ScrollPane middleContainer;
    @FXML private ScrollPane leftPanelContainer;
    @FXML private ScrollPane footerContainer;


    //TODO: Implement the Api Call
    private int nodeID;
    private String mediaType;
    private static final String ComponentController = "controller";
    private final VBox rightPanel = InfoPageFactory.createRightPanel();
    private final HBox header = InfoPageFactory.createHeader();
    private final VBox leftPanel = InfoPageFactory.createLeftPanel();
    private final HBox footer = InfoPageFactory.createFooter();
    private final VBox middle = InfoPageFactory.createMiddlePanel();
    private final DynamicPageHistory history = new DynamicPageHistory();

    public void externalUpdateMethod(int nodeID, String media_type) throws IOException {
        this.nodeID = nodeID;
        this.mediaType = media_type;
        MediaInfoPageModel infoPageModel = getJsonTestString(mediaType);
        getAndBuild(infoPageModel);
        history.addBack(new Pair<>(nodeID, media_type));
        buttonChecker();
    }

    private void internalUpdateMethod(int nodeID, String mediaType) throws IOException {
        this.nodeID = nodeID;
        this.mediaType = mediaType;
        MediaInfoPageModel infoPageModel = getJsonTestString(mediaType);
        buttonChecker();
        getAndBuild(infoPageModel);
    }

    private void getAndBuild(MediaInfoPageModel infopage){
        if (infopage instanceof MovieInfoPageModel){
            buildMoviePage((MovieInfoPageModel) infopage);
        } else if (infopage instanceof TvInfoPageModel) {
            System.out.println("To Be Implemented");
        } else {
            throw new IllegalStateException("Unsupported Media Type");
        }
    }

    private void buildMoviePage(MovieInfoPageModel jsonString){
        setRightPanelContainer(jsonString);
        setHeader(jsonString);
        setLeftPanel(jsonString);
        setFooter(jsonString);
        setMiddlePanel(jsonString);
    }

    private void setRightPanelContainer(MovieInfoPageModel string){
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

    private void setFooter(MovieInfoPageModel string){
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

    private MediaInfoPageModel getJsonTestString(String mediaType) throws IOException {
        if (mediaType.equals("movie")){
            return MediaInfoPageModelFactory.fromJson(TestJsonStringHolder.getJsonStringMovie(),mediaType);
        } else if (mediaType.equals("tv")){
            return MediaInfoPageModelFactory.fromJson(TestJsonStringHolder.getJsonStringTV(), mediaType);
        }
        return null;
    }

    public void setBackButton() {
        backButton.setOnMouseClicked(event -> {
            Pair<Integer, String> newpair = history.goBack();
            if (newpair != null) {
                try {
                    internalUpdateMethod(newpair.getFirst(), newpair.getSecond());
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
    }

    private void buttonChecker(){
        backButton.setVisible(!history.historyIsEmpty());
    }

    public void initProperties() {
        setBackButton();
    }
}
