package com.example.MyWatchList.Controllers;

import com.example.MyWatchList.DataClasses.PfpDictionary;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class SettingsPageController {
    DropShadow shadow = new DropShadow();
    PfpDictionary dic = new PfpDictionary();
    HBox pfpHBox;
    Button confPFP;
    Button cancelPFP;
    GridPane pfpGrid;
    ImageView profilePICS;

    private ImageView lastSelectedPfp;

    SettingsPageController(HBox pfpHBox, Button confPFP, Button cancelPFP, GridPane pfpGrid, ImageView profilePICS){
        this.pfpHBox = pfpHBox;
        this.confPFP = confPFP;
        this.cancelPFP = cancelPFP;
        this.pfpGrid = pfpGrid;
        this.profilePICS = profilePICS;

        dic.Dictionary();
    }

}
