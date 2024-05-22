package com.example.MyWatchList.Controllers.CommonComponent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ReviewPopOverController {
    @FXML private Label userNameLabel;
    @FXML private Label createdAtLabel;
    @FXML private Label lastUpdatedLabel;
    @FXML private TextArea contentArea;

    private String content;
    private String createdAt;
    private String updatedAt;
    private String userName;

    public void initPopOver(String content, String createdAt, String updatedAt, String userName) {
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userName = userName;
        makePopOver();
    }

    public void makePopOver(){
        createdAtLabel.setText("Created: " + formatDate(createdAt));
        lastUpdatedLabel.setText("Last Updated: " + formatDate(updatedAt));
        userNameLabel.setText(userName);
        contentArea.setText(formatContent(content));
    }

    private String formatDate(String text){
        return text.substring(0,11);
    }

    private String formatContent(String content){
        String formatted = content.replace("<em>", "*").replace("</em>", "*");
        return formatted.replace("\r\n", "\n");
    }
}