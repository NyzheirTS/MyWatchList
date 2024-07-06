package com.example.MyWatchList.Controllers.DynamicPages.SearchPage;

import com.example.MyWatchList.ApiClass.ApiConnection;
import com.example.MyWatchList.Controllers.DynamicPages.MovieInfoPages.MovieInfoPageController;
import com.example.MyWatchList.DataModels.SearchModels.SearchModel;
import com.example.MyWatchList.DataModels.Utils.UrlBuilder;
import com.example.MyWatchList.TestFolder.TestJsonStringHolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.fxmisc.flowless.Cell;
import org.fxmisc.flowless.VirtualFlow;
import org.fxmisc.flowless.VirtualizedScrollPane;

import java.io.IOException;

public class SearchPageController {

    private static SearchPageController instance;
    @FXML private BorderPane basePane;
    @FXML private Label searchLabel;
    @FXML private Button backButton;
    @FXML private Label pageIndex;
    @FXML private Button fwordButton;

    private ObservableList<BorderPane> items = FXCollections.observableArrayList();
    private final VirtualFlow<BorderPane, ?> baseFlow = VirtualFlow.createVertical(items, this::regionCell);
    private final VirtualizedScrollPane<VirtualFlow<BorderPane, ?>> virtualScrollPane = new VirtualizedScrollPane<>(baseFlow, ScrollPane.ScrollBarPolicy.NEVER, ScrollPane.ScrollBarPolicy.ALWAYS);
    private SearchModel model;
    private int pageNumber = 1;
    private int maxPage;
    private String query;


    public void update(String query) throws IOException {
        this.query = query;
        model = SearchModel.fromJson(ApiConnection.getInstance().onDemandApiCall(UrlBuilder.getTmdbSearchQuery(query, pageNumber)));
        //model = SearchModel.fromJson(TestJsonStringHolder.getJsonStringSearchPage());
        maxPage = model.getTotal_pages();
        checkButtons();
        searchLabel.setText( "Results For : " + query);
        updateIndex();
        items = getALlItems(model.getResults());
    }

    private void updateIndex(){
        pageIndex.setText(pageNumber + " / " + maxPage);
    }


    public void initMethods(){
        setBackButton();
        setForwardButton();
        basePane.setCenter(virtualScrollPane);
        checkButtons();
    }

    public ObservableList<BorderPane> getALlItems(SearchModel.Results[] results){
        items.clear();
        for (SearchModel.Results result : results){
            BorderPane node;
            if (!result.getMedia_type().equals("person")) {
             node = SearchPageFactory.createMediaInfoCard(
                result.getPoster_path(),
                result.getName(),
                result.getOverview(),
                result.getVote_average(),
                result.getId(),
                result.getMedia_type()
             );
            } else {
                node = SearchPageFactory.createPersonInfoCard(
                    result.getProfile_path(),
                    result.getName(),
                    result.getKnown_for(),
                    result.getId()
                );
            }
            items.add(node);
        }
        baseFlow.showAsFirst(0);
        return items;
    }

    private void checkButtons(){
        backButton.setVisible(pageNumber > 1);
        fwordButton.setVisible(pageNumber!=maxPage);
    }
    private void setBackButton(){
        backButton.setOnAction(event -> {
            try {
                pageNumber -= 1;
                model = SearchModel.fromJson(ApiConnection.getInstance().onDemandApiCall(UrlBuilder.getTmdbSearchQuery(query, pageNumber)));
                items = getALlItems(model.getResults());
                updateIndex();
                checkButtons();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void setForwardButton(){
        fwordButton.setOnAction(event -> {
            try {
                pageNumber += 1;
                model = SearchModel.fromJson(ApiConnection.getInstance().onDemandApiCall(UrlBuilder.getTmdbSearchQuery(query, pageNumber)));
                items = getALlItems(model.getResults());
                updateIndex();
                checkButtons();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }



    private Cell<BorderPane, ?> regionCell(BorderPane box){
        return Cell.wrapNode(box);
    }
    public static SearchPageController getInstance(){
        return SearchPageController.instance;
    }

    public static void setInstance(SearchPageController instance){
        SearchPageController.instance = instance;
    }
}
