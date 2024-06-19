package com.example.MyWatchList.Controllers.DynamicPages.PersonPage;

import com.example.MyWatchList.Controllers.CommonComponent.CommonFactory;
import com.example.MyWatchList.DataModels.CommonModels.ExternalIds;
import com.example.MyWatchList.DataModels.Utils.LogosMap;
import com.example.MyWatchList.DataModels.PersonModels.PersonModel;
import com.example.MyWatchList.DataModels.Utils.UrlBuilder;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PersonHeaderController {

    private static PersonHeaderController instance;

    @FXML private VBox logoBox;
    @FXML private ImageView personImage;
    @FXML private Label name;
    @FXML private Label placeofbirth;
    @FXML private TextArea biography;

    public void update(PersonModel string) {
        setPersonImage(string);
        setMiddle(string);
        setExternalLinks(string);
    }


    private void setPersonImage(PersonModel string){
        personImage.setImage(null);
        Task<Object> imageLoadingTask = new Task<>() {
            @Override
            protected Void call() {
                Image loadedImage = new Image(UrlBuilder.getBasePosterw300(string.getProfile_path()), true);
                Platform.runLater(() -> personImage.setImage(loadedImage));
                return null;
            }
        };
        new Thread(imageLoadingTask).start();
    }

    private void setMiddle(PersonModel string){
        Platform.runLater(() -> {
            name.setText(string.getName());
            placeofbirth.setText(string.getPlace_of_birth());
            biography.setText(string.getBiography());
        });
    }

    private void setExternalLinks(PersonModel string){
        logoBox.getChildren().clear();
        ExternalIds ids = string.getExternal_ids();
            for (Method method : ExternalIds.class.getDeclaredMethods()){
                if (method.getName().startsWith("get")){
                    try {
                        String value = (String) method.invoke(ids);
                        if (value != null && !value.isEmpty()) {
                            String key = method.getName().substring(3).toLowerCase();
                            Platform.runLater(() -> {
                                AnchorPane node = CommonFactory.createLogo(
                                        LogosMap.URL_MAP.get(key) + value,
                                        getClass().getResource(LogosMap.IMAGE_MAP.get(key))
                                );
                                logoBox.getChildren().add(node);
                            });
                        }
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
    }


    //logo 35x34

    public static PersonHeaderController getInstance() {return PersonHeaderController.instance;}
    public static void setInstance(PersonHeaderController instance) {
        PersonHeaderController.instance = instance;}
}
