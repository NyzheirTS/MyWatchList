package com.example.MyWatchList.Controllers.HistoryManager;

import com.example.MyWatchList.Controllers.DynamicPages.CollectionPage.CollectionPageController;

import java.io.IOException;

public class UpdateCollectionPageCommand implements Command {
    private final int pageID;
    private final Runnable runnable;

    public UpdateCollectionPageCommand(int pageID, Runnable runnable){
        this.pageID = pageID;
        this.runnable = runnable;
    }

    @Override
    public void execute() throws IOException {
        CollectionPageController.getInstance().update(pageID);
        runnable.run();
    }
}
