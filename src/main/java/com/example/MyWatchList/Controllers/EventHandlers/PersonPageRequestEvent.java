package com.example.MyWatchList.Controllers.EventHandlers;

import javafx.event.Event;
import javafx.event.EventType;

public class PersonPageRequestEvent extends Event {
    public static final EventType<PersonPageRequestEvent> ACTOR_ACTRESS_PAGE_REQUEST = new EventType<>("ACTOR_ACTRESS_PAGE_REQUEST");
    private final int id;

    public PersonPageRequestEvent(EventType<? extends Event> eventType, int id){
        super(eventType);
        this.id = id;
    }
    public int getId() {return id;}
}
