package com.example.MyWatchList.Controllers.HistoryManager;

import com.example.MyWatchList.Controllers.CommonComponent.CastCrewPages.CastCrewPageController;
import com.example.MyWatchList.DataModels.CommonModels.CreditsModel;

import java.io.IOException;

public class UpdateCastCrewPageCommand implements Command{
    private final CreditsModel string;
    private final Runnable runnable;

    public UpdateCastCrewPageCommand(CreditsModel string, Runnable runnable){
        this.string = string;
        this.runnable = runnable;
    }

    @Override
    public void execute() throws IOException {
        CastCrewPageController.getInstance().update(string);
        runnable.run();
    }
}
