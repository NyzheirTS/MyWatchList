package com.example.MyWatchList.Controllers.DynamicPages;

import com.example.MyWatchList.Controllers.CommonComponent.CommonFormatter;
import com.example.MyWatchList.DataModels.CommonModels.ProductionCompaniesModel;
import com.example.MyWatchList.DataModels.CommonModels.ProductionCountriesModel;
import com.example.MyWatchList.DataModels.MovieModels.MovieInfoPageModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class LeftPanelController {

    @FXML private VBox innerParentVbox;
    @FXML private Label runtimeLabel;
    @FXML private Label collectionLabel;
    @FXML private VBox productionCompanieVBox;
    @FXML private VBox productionCountriesVBox;
    @FXML private Label budgetLabel;
    @FXML private Label revenueLabel;
    @FXML private Label releasedateLabel;


    public void updateLeftPanel(MovieInfoPageModel jsonString){
        setLabels(jsonString);
        setProductionCompanies(jsonString);
        setProductionCountries(jsonString);
    }

    private void setLabels(MovieInfoPageModel jsonString){
        runtimeLabel.setText(CommonFormatter.formateRuntime(jsonString.getRuntime()));
        collectionLabel.setText(jsonString.getBelongs_to_collection() != null ? jsonString.getBelongs_to_collection().getName() : "N/A");
        budgetLabel.setText(CommonFormatter.formatMoney(jsonString.getBudget()));
        revenueLabel.setText(CommonFormatter.formatMoney(jsonString.getRevenue()));
        releasedateLabel.setText(jsonString.getRelease_date() != null ? jsonString.getRelease_date() : "N/A");
    }

    private void setProductionCompanies(MovieInfoPageModel jsonString){
        productionCompanieVBox.getChildren().clear();
        ProductionCompaniesModel[] productionCompanyModels = jsonString.getProduction_companies();
        if (productionCompanyModels.length == 0) {
            productionCompanieVBox.getChildren().add(formattedLabel("N/A"));
        } else {
            for (ProductionCompaniesModel companies : productionCompanyModels) {
                productionCompanieVBox.getChildren().add(formattedLabel(companies.getName()));
            }
        }
    }

    private void setProductionCountries(MovieInfoPageModel jsonString){
        productionCountriesVBox.getChildren().clear();
        ProductionCountriesModel[] productionCountriesModel = jsonString.getProduction_countries();
        if (productionCountriesModel.length == 0){
            productionCompanieVBox.getChildren().add(formattedLabel("N/A"));
        } else {
            for (ProductionCountriesModel countries : productionCountriesModel) {
                productionCountriesVBox.getChildren().add(formattedLabel(countries.getName()));
            }
        }
    }

    private Label formattedLabel(String name){
        Label label = new Label(name);
        label.setFont(new Font("Arimo", 20));
        return label;
    }
}
