package com.example.MyWatchList.AppEntry.HistoryManagement;

import com.example.MyWatchList.Controllers.InfoPage.InfoPageController;

import java.io.IOException;

public class UpdateInfoPageCommand implements Command{

    private final InfoPageController controller;
    private final int nodeID;
    private final String mediaType;
    private final Runnable runnable;

    public UpdateInfoPageCommand(InfoPageController controller, int nodeID, String mediaType, Runnable runnable){
        this.controller = controller;
        this.nodeID = nodeID;
        this.mediaType = mediaType;
        this.runnable = runnable;
    }
    @Override
    public void execute() throws IOException {
        controller.updatePage(nodeID, mediaType);
        runnable.run();
    }
}
