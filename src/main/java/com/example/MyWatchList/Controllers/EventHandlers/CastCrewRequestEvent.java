package com.example.MyWatchList.Controllers.EventHandlers;

import com.example.MyWatchList.DataModels.CommonModels.CreditsModel;
import com.example.MyWatchList.DataModels.CommonModels.MediaInfoPageModel;
import javafx.event.Event;
import javafx.event.EventType;

public class CastCrewRequestEvent extends Event{
    public static final EventType<CastCrewRequestEvent> CAST_CREW_PAGE_REQUEST = new EventType<>("CAST_CREW_PAGE_EVENT");
    private final CreditsModel string;

    public CastCrewRequestEvent(EventType<? extends Event> eventType, CreditsModel string) {
        super(eventType);
        this.string = string;
    }

    public CreditsModel getString() {
        return string;
    }
}
