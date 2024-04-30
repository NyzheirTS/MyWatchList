package com.example.MyWatchList.Controllers.CommonComponent;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.Node;

public class EventRequest extends Event {
    public static final EventType<EventRequest> INFO_PAGE_REQUEST = new EventType<>(Event.ANY, "INFO_PAGE_REQUEST");

    private final int nodeNumber;
    private final String media_type;

    public EventRequest(EventType<? extends Event> eventType, int nodeNumber, String media_type) {
        super(eventType);
        this.nodeNumber = nodeNumber;
        this.media_type = media_type;
    }

    public int getNodeNumber() {
        return nodeNumber;
    }

    public String getMedia_type() {
        return media_type;
    }
}
