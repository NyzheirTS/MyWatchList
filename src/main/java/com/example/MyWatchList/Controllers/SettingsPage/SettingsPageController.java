package com.example.MyWatchList.Controllers.SettingsPage;


import com.example.MyWatchList.AppConfig.Theme;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import java.util.prefs.Preferences;


public class SettingsPageController {
    private final Preferences prefs = Preferences.userNodeForPackage(SettingsPageController.class);

    @FXML private ChoiceBox<String> themeChoiceBox;
    private final String[] themeList = {
            Theme.CupertinoLight.getName(),
            Theme.CupertinoDark.getName(),
            Theme.Dracula.getName(),
            Theme.NordLight.getName(),
            Theme.NordDark.getName(),
            Theme.PrimerDark.getName(),
            Theme.PrimerLight.getName()
    };


    public void initSettingsPage(){
        setThemeChoiceBox();
        initTheme();
        //clearAllPreferences();
    }



    private void setThemeChoiceBox(){
        themeChoiceBox.setItems(FXCollections.observableArrayList(themeList));
        themeChoiceBox.setOnAction(event -> updateTheme(themeChoiceBox.getValue()));
    }
    public void initTheme(){
        String themeName = prefs.get("theme", Theme.CupertinoDark.getName()); //set selected or push default
        try {
            Theme theme = Theme.valueOf(themeName.replace(" ", ""));
            theme.apply();
            themeChoiceBox.setValue(themeName);
        } catch (IllegalArgumentException e){
            System.err.println("Invalid theme in preferences, default to CupertinoDark");
            Theme.CupertinoDark.apply();
            themeChoiceBox.setValue(Theme.CupertinoDark.getName());
        }
    }

    private void updateTheme(String themeName){
        Theme theme = Theme.valueOf(themeName.replace(" ", ""));
        theme.apply();
        prefs.put("theme", themeName);
    }


    /*
    public void clearAllPreferences() {
        try {
            prefs.clear(); // Clear all preferences in this node
            prefs.flush(); // Ensure the changes are persisted immediately
        } catch (BackingStoreException e) {
            e.printStackTrace(); // Handle or log exception
        }
    }
     */


}
