package com.example.MyWatchList.Controllers.DynamicPages.ActorActressPage;

import com.example.MyWatchList.DataModels.PersonModels.ActorActressModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

public class ActorActressHeaderController {

    private static ActorActressHeaderController instance;

    @FXML private ImageView personImage;
    @FXML private Label name;
    @FXML private Label placeofbirth;
    @FXML private TextArea biography;

    public void update(ActorActressModel string){
        //TODO
    }

    public static ActorActressHeaderController getInstance() {return ActorActressHeaderController.instance;}
    public static void setInstance(ActorActressHeaderController instance) {ActorActressHeaderController.instance = instance;}
}
