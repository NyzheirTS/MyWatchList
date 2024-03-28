package com.example.MyWatchList.InfoPage;
import javafx.event.Event;
import javafx.event.EventType;
public class InfoPageRequestEvent extends Event {
    public static final EventType<InfoPageRequestEvent> INFO_PAGE_REQUEST = new EventType<>(Event.ANY, "INFO_PAGE_REQUEST");
    public InfoPageRequestEvent(){
        super(INFO_PAGE_REQUEST);
    }
}
