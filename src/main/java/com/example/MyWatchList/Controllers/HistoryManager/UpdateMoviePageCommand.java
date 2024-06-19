package com.example.MyWatchList.Controllers.HistoryManager;

import com.example.MyWatchList.Controllers.DynamicPages.MovieInfoPages.MovieInfoPageController;

import java.io.IOException;

public class UpdateMoviePageCommand implements Command {
    private final int nodeID;
    private final Runnable runnable;

    public UpdateMoviePageCommand(int nodeID, Runnable runnable){
        this.nodeID = nodeID;
        this.runnable = runnable;
    }
    @Override
    public void execute() throws IOException {
        MovieInfoPageController.getInstance().update(nodeID);
        runnable.run();
    }


}
