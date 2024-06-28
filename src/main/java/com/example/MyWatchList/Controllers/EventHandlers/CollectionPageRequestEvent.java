package com.example.MyWatchList.Controllers.EventHandlers;

import javafx.event.Event;
import javafx.event.EventType;

public class CollectionPageRequestEvent extends Event {
    public static final EventType<CollectionPageRequestEvent> COLLECTION_PAGE_REQUEST = new EventType<>("COLLECTION_PAGE_REQUEST");
    private final int id;

    public CollectionPageRequestEvent(EventType<? extends  Event> eventType, int id){
        super(eventType);
        this.id = id;
    }

    public int getId(){return id;}

}
