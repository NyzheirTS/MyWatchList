package com.example.MyWatchList.Controllers.CommonComponent;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.Node;

import java.beans.Transient;
import java.io.Serializable;

public class EventRequest extends Event {
    public static final EventType<EventRequest> INFO_PAGE_REQUEST = new EventType<>(Event.ANY, "INFO_PAGE_REQUEST");
    private Node contentNode;
    public EventRequest(EventType<? extends Event> eventType, Node borderPane) {
        super(eventType);
        this.contentNode = borderPane;
    }
    public Node getContentNode() {
        return contentNode;
    }
}
