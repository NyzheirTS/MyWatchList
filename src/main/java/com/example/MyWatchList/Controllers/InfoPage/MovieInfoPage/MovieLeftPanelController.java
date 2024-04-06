package com.example.MyWatchList.Controllers.InfoPage.MovieInfoPage;

import com.example.MyWatchList.AppConfig.AppCleaner;
import com.example.MyWatchList.Controllers.CommonComponent.CommonFormatter;
import com.example.MyWatchList.DataModels.MovieModels.MovieInfoPageModel;
import com.example.MyWatchList.DataModels.CommonModels.ProductionCompaniesModel;
import com.example.MyWatchList.DataModels.CommonModels.ProductionCountriesModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MovieLeftPanelController implements AppCleaner {
    @FXML
    private VBox innerParentVbox;
    @FXML
    private Label runtimeLabel;
    @FXML
    private Label collectionLabel;
    @FXML
    private VBox productionCompanieVBox;
    @FXML
    private VBox productionCountriesVBox;
    @FXML
    private Label budgetLabel;
    @FXML
    private Label revenueLabel;
    @FXML
    private Label releasedateLabel;

    private MovieInfoPageModel jsonString;

    public void initLeftPanel(MovieInfoPageModel jsonString){
        this.jsonString = jsonString;
        Platform.runLater(() -> {
            setLabels();
            setProductionCompanies();
            setProductionCountries();
        });
    }

    private void setLabels(){
        runtimeLabel.setText(CommonFormatter.formateRuntime(jsonString.getRuntime()));
        collectionLabel.setText(jsonString.getBelongs_to_collection().getName());
        budgetLabel.setText(CommonFormatter.formatMoney(jsonString.getBudget()));
        revenueLabel.setText(CommonFormatter.formatMoney(jsonString.getRevenue()));
        releasedateLabel.setText(jsonString.getRelease_date());
    }

    private void setProductionCompanies(){
        ProductionCompaniesModel[] productionCompanyModels = jsonString.getProduction_companies();
        for (ProductionCompaniesModel companies : productionCompanyModels){
            productionCompanieVBox.getChildren().add(formattedLabel(companies.getName()));
        }
    }

    private void setProductionCountries(){
        ProductionCountriesModel[] productionCountriesModel = jsonString.getProduction_countries();
        for (ProductionCountriesModel countries : productionCountriesModel){
            productionCountriesVBox.getChildren().add(formattedLabel(countries.getName()));
        }
    }

    private Label formattedLabel(String name){
        Label label = new Label(name);
        label.setFont(new Font("Arimo", 20));
        return label;
    }


    @Override
    public void cleanup() {
        productionCompanieVBox.getChildren().clear();
        productionCountriesVBox.getChildren().clear();
    }
}