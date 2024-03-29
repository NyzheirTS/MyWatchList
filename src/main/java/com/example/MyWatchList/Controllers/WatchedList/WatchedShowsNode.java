package com.example.MyWatchList.Controllers.WatchedList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class WatchedShowsNode {
    private final Node node;
    private final Button editSaveButton;
    private final TextField exitText;
    private final Label label;
    private final Button deleteButton;
    private final int nodeNumber;
    private Alert alert;

    public WatchedShowsNode(String labelText, int nodeNumber){
        try {
            node = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("list-item-template.fxml")));


            label = (Label) node.lookup("#titleLabel");
            label.setText(labelText);

            exitText = (TextField) node.lookup("#moneyTxtField");

            editSaveButton = (Button) node.lookup("#editButton");
            deleteButton = (Button) node.lookup("#deletButton");

            if(editSaveButton != null){
                handleEditMode();
            }
            if (deleteButton != null){
                handleDeleteMode();
            }

            this.nodeNumber = nodeNumber;

            setNodeMouseEvents();
        } catch (IOException e) {
            throw new RuntimeException(Arrays.toString(e.getStackTrace()));
        }
    }


    private void handleEditMode(){
        editSaveButton.setCursor(Cursor.HAND);
        editSaveButton.setOnAction(event ->{
            if("Edit".equals(editSaveButton.getText())){

                editSaveButton.setText("Save");
                exitText.setText(label.getText());
                label.setVisible(false);
                exitText.setVisible(true);

                System.out.println("Edit on Node: " + nodeNumber);
            }else {

                editSaveButton.setText("Edit");
                label.setText(exitText.getText());
                label.setVisible(true);
                exitText.setVisible(false);

                System.out.println("Save on Node: " + nodeNumber);
            }
        });
    }


    private void handleDeleteMode() {
        deleteButton.setCursor(Cursor.HAND);
        deleteButton.setOnAction(event -> {
            conformationPopup();
            alert.showAndWait().ifPresent(response ->{
                if(response == ButtonType.OK){
                    if(node.getParent() instanceof VBox parentVbox){
                        parentVbox.getChildren().remove(node);
                    }
                    System.out.println("Deleted Node: " + nodeNumber);
                }else{
                    alert.hide();
                }
            });
        });
    }


    private void conformationPopup(){
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Deletion");
        alert.setContentText("Confirm Deletion of Row: " + (nodeNumber+1));

        //Customize the visuals of the alert stage.
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("C:\\Users\\eshas\\IdeaProjects\\Test_UI-JFX\\src\\main\\resources\\com\\example\\MyWatchList\\images\\First Quarter.png"));

    }


    private void setNodeMouseEvents(){
        node.setOnMouseEntered(event -> node.setStyle("-fx-background-color : #0A0E3F"));
        node.setOnMouseExited(event -> node.setStyle("-fx-background-color : black"));
    }

    public void bindWidth(VBox vbox){
        HBox hbox = (HBox) node;
        hbox.prefWidthProperty().bind(vbox.widthProperty());
    }


    public Node getNode(){
        return node;
    }

}
