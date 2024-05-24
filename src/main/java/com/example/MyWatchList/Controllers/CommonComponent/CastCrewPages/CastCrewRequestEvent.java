package com.example.MyWatchList.Controllers.CommonComponent.CastCrewPages;

import com.example.MyWatchList.DataModels.CommonModels.MediaInfoPageModel;
import javafx.event.Event;
import javafx.event.EventType;

public class CastCrewRequestEvent extends Event{
    public static final EventType<CastCrewRequestEvent> CAST_CREW_PAGE_REQUEST = new EventType<>("CAST_CREW_PAGE_EVENT");
    private MediaInfoPageModel string;

    public CastCrewRequestEvent(EventType<? extends Event> eventType, MediaInfoPageModel string) {
        super(eventType);
        this.string = string;
    }

    public MediaInfoPageModel getString() {
        return string;
    }
}
