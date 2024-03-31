package com.example.MyWatchList.Controllers.InfoPage;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class InfoPageRequestEvent extends Event {
    public static final EventType<InfoPageRequestEvent> INFO_PAGE_REQUEST = new EventType<>(Event.ANY, "INFO_PAGE_REQUEST");

    // Add a private VBox field
    private BorderPane borderPane;

    // Modify the constructor to take a VBox as an argument
    public InfoPageRequestEvent(BorderPane borderPane) {
        super(INFO_PAGE_REQUEST);
        this.borderPane = borderPane; // Set the VBox field
    }

    // Getter method for the VBox

    public BorderPane getBorderPane() {
        return borderPane;
    }
}
