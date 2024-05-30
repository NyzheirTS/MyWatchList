package com.example.MyWatchList.Controllers.HistoryManager;

import com.example.MyWatchList.Controllers.DynamicPages.MovieInfoPages.MovieInfoPageController;
import com.example.MyWatchList.Controllers.DynamicPages.TvInfoPages.TvInfoPageController;

import java.io.IOException;

public class UpdateInfoPageCommand implements Command {
    private final int nodeID;
    private final String mediaType;
    private final Runnable runnable;

    public UpdateInfoPageCommand( int nodeID, String mediaType, Runnable runnable){
        this.nodeID = nodeID;
        this.mediaType = mediaType;
        this.runnable = runnable;
    }
    @Override
    public void execute() throws IOException {
        switcher();
        runnable.run();
    }

    private void switcher() throws IOException {
        if (mediaType.equals("movie")){
            MovieInfoPageController.getInstance().update(nodeID, mediaType);
        } else if (mediaType.equals("tv")) {
            TvInfoPageController.getInstance().update(nodeID, mediaType);
        }
    }
}
