package Interfaces;

import javafx.scene.Node;
import javafx.scene.layout.VBox;

public interface NodeInterface {

    void handleEditMode();
    void handleDeleteMode();
    void conformationPopup();
    void setNodeMouseEvents();
    void bindWidth(VBox vbox);
    Node getNode();

}
