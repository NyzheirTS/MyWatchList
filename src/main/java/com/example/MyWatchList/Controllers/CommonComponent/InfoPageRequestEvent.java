package com.example.MyWatchList.Controllers.CommonComponent;
import javafx.event.Event;
import javafx.event.EventType;

public class InfoPageRequestEvent extends Event {
    public static final EventType<InfoPageRequestEvent> INFO_PAGE_REQUEST = new EventType<>(Event.ANY, "INFO_PAGE_REQUEST");

    private final int nodeNumber;
    private final String media_type;

    public InfoPageRequestEvent(EventType<? extends Event> eventType, int nodeNumber, String media_type) {
        super(eventType);
        this.nodeNumber = nodeNumber;
        this.media_type = media_type;
    }

    public int getNodeNumber() {
        return nodeNumber;
    }

    public String getMedia_Type() {
        return media_type;
    }
}
