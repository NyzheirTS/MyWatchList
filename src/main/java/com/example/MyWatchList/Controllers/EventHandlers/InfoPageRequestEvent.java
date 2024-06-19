package com.example.MyWatchList.Controllers.EventHandlers;
import javafx.event.Event;
import javafx.event.EventType;

public class InfoPageRequestEvent extends Event {
    public static final EventType<InfoPageRequestEvent> MOVIE_PAGE_REQUEST = new EventType<>(Event.ANY, "MOVIE_PAGE_REQUEST");
    public static final EventType<InfoPageRequestEvent> TV_PAGE_REQUEST = new EventType<>(Event.ANY, "TV_PAGE_REQUEST");

    private final int nodeNumber;

    public InfoPageRequestEvent(EventType<? extends Event> eventType, int nodeNumber) {
        super(eventType);
        this.nodeNumber = nodeNumber;
    }

    public int getNodeNumber() {
        return nodeNumber;
    }

}
