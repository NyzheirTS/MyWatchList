package com.example.MyWatchList.Controllers.HistoryManager;

import com.example.MyWatchList.Controllers.DynamicPages.PersonPage.PersonPageController;

import java.io.IOException;

public class UpdatePersonPageCommand implements Command{
    private final Runnable runnable;
    private final int id;

    public UpdatePersonPageCommand(int id, Runnable runnable){
        this.id = id;
        this.runnable = runnable;
    }

    @Override
    public void execute() throws IOException {
        PersonPageController.getInstance().update(id);
        runnable.run();
    }
}
