package com.example.MyWatchList.Controllers.DynamicPages.TvInfoPages;

import com.example.MyWatchList.Controllers.DynamicPages.InfoPageFactory;
import com.example.MyWatchList.Controllers.DynamicPages.RightPanelController;
import com.example.MyWatchList.Controllers.EventHandlers.CastCrewRequestEvent;
import com.example.MyWatchList.DataModels.CommonModels.MediaInfoPageModelDeserializer;
import com.example.MyWatchList.DataModels.CommonModels.RecommendationsModel;
import com.example.MyWatchList.DataModels.TvModels.TvSeriesModel;
import com.example.MyWatchList.TestFolder.TestJsonStringHolder;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class TvInfoPageController {

    @FXML private ScrollPane rightPanelContainer;
    @FXML private Hyperlink linkToCastCrewPage;



    private static TvInfoPageController instance;
    private static final String ComponentController = "controller";
    private int nodeID;
    private String mediaType;
    private final VBox rightPanel = InfoPageFactory.createRightPanel();
    private TvSeriesModel model;



    public void update(int nodeid, String mediaType) throws IOException {
        this.nodeID = nodeid;
        this.mediaType = mediaType;
        model = MediaInfoPageModelDeserializer.fromJson(TestJsonStringHolder.getJsonStringTV(), TvSeriesModel.class);
        buildTvPage(model);
    }

    public void initMethods(){
        setHyperLink();
    }

    private void buildTvPage(TvSeriesModel jsonString){
        setRightPanelContainer(jsonString.getRecommendations());

    }

    private void setRightPanelContainer(RecommendationsModel string){
        if (rightPanel != null && rightPanel.getProperties().containsKey(ComponentController)) {
            RightPanelController rightPanelController = (RightPanelController) rightPanel.getProperties().get(ComponentController);
            rightPanelController.updateRightPanel(string, mediaType);
            rightPanelContainer.setContent(rightPanel);
        }
    }


    public void setHyperLink(){
        linkToCastCrewPage.setOnMouseClicked(event -> {
            CastCrewRequestEvent infoPageRequestEvent = new CastCrewRequestEvent(CastCrewRequestEvent.CAST_CREW_PAGE_REQUEST, model.getCredits());
            linkToCastCrewPage.fireEvent(infoPageRequestEvent);
            infoPageRequestEvent.consume();
        });
    }
    public static void setInstance(TvInfoPageController controller) {
        TvInfoPageController.instance = controller;
    }

    public static TvInfoPageController getInstance(){
        return TvInfoPageController.instance;
    }
}
