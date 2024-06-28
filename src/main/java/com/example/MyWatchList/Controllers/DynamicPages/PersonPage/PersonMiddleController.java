package com.example.MyWatchList.Controllers.DynamicPages.PersonPage;

import com.example.MyWatchList.DataModels.CommonModels.CC;
import com.example.MyWatchList.DataModels.CommonModels.CombinedCredits;
import com.example.MyWatchList.DataModels.Utils.QuickSort;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import org.fxmisc.flowless.Cell;
import org.fxmisc.flowless.VirtualFlow;
import org.fxmisc.flowless.VirtualizedScrollPane;

import java.util.List;

public class PersonMiddleController {

    @FXML private BorderPane parent;
    @FXML private ChoiceBox<String> filterBox;

    private static PersonMiddleController instance;
    private final  String[] actionList = {"All","Cast","Crew","Rating Up","Rating Down"};
    private ObservableList<HBox> items = FXCollections.observableArrayList();
    private final VirtualFlow<HBox, ?> bf = VirtualFlow.createVertical(items, this::regionCell);
    private final VirtualizedScrollPane<VirtualFlow<HBox, ?>> vf = new VirtualizedScrollPane<>(bf, ScrollPane.ScrollBarPolicy.NEVER, ScrollPane.ScrollBarPolicy.ALWAYS);
    private CombinedCredits string;


    public void update(CombinedCredits string) {
        this.string = string;
        filterBox.setValue(actionList[0]);
        items = getAllItems(string);
    }

    public void initContainer(){
        parent.setCenter(vf);
        filterBox.setItems(FXCollections.observableArrayList(actionList));
        filterBox.setOnAction(event -> filter(filterBox.getValue()));
    }

    private void filter(String name){
        String filter = name.replace(" ", "").toLowerCase();
        switch  (filter){
            case "all":
                items = getAllItems(string);
                break;
            case "cast":
                items = getCastItems(string);
                break;
            case "crew":
                items = getCrewItems(string);
                break;
            case "ratingup":
                items = getRatingUp(string);
                break;
            case "ratingdown":
                items = getRatingDown(string);
                break;
            default:
                System.out.println("Default");
        }
    }


    private ObservableList<HBox> getAllItems(CombinedCredits str){
        items.clear();
        List<CC> sff = str.getBoth();
        for (CC ss : sff){
            HBox node = PersonPageFactory.createPersonInfoCard(
                    ss.getPoster_path(),
                    ss.getTitle(),
                    ss.getCharacter(),
                    ss.getRelease_date(),
                    ss.getVote_average(),
                    ss.getId(),
                    ss.getMedia_type()
            );
            items.add(node);
        }
        bf.showAsFirst(0);
        return items;
    }

    private ObservableList<HBox> getCastItems(CombinedCredits cc){
        items.clear();
        CombinedCredits.Cast[] credits = cc.getCast();
        for (CombinedCredits.Cast cast : credits){
            HBox node = PersonPageFactory.createPersonInfoCard(
                    cast.getPoster_path(),
                    cast.getTitle(),
                    cast.getCharacter(),
                    cast.getRelease_date(),
                    cast.getVote_average(),
                    cast.getId(),
                    cast.getMedia_type()
            );
            items.add(node);
        }
        bf.showAsFirst(0);
        return items;
    }

    private ObservableList<HBox> getCrewItems(CombinedCredits cc){
        items.clear();
        CombinedCredits.Crew[] credits = cc.getCrew();
        for (CombinedCredits.Crew crew : credits){
            HBox node = PersonPageFactory.createPersonInfoCard(
                    crew.getPoster_path(),
                    crew.getTitle(),
                    crew.getCharacter(),
                    crew.getRelease_date(),
                    crew.getVote_average(),
                    crew.getId(),
                    crew.getMedia_type()
            );
            items.add(node);
        }
        bf.showAsFirst(0);
        return items;
    }
    private ObservableList<HBox> getRatingUp(CombinedCredits cc){
        items = getAllItems(cc);
        return (ObservableList<HBox>) QuickSort.sortScoreUp(items, 0, items.size() - 1);
    }
    private ObservableList<HBox> getRatingDown(CombinedCredits cc){
        items = getAllItems(cc);
        return (ObservableList<HBox>) QuickSort.sortScoreDown(items, 0, items.size() -1);
    }


    private Cell<HBox, ?> regionCell(HBox box){
        return Cell.wrapNode(box);
    }


    public static PersonMiddleController getInstance() {return PersonMiddleController.instance;}
    public static void setInstance(PersonMiddleController instance) {
        PersonMiddleController.instance = instance;}

    public String[] getActionList() {
        return actionList;
    }
}
