package com.example.MyWatchList.AppEntry.HistoryManagement;

import java.io.IOException;

public class UpdateStaticPageCommand implements Command{
    private final Runnable runnable;

    public UpdateStaticPageCommand(Runnable runnable){
        this.runnable = runnable;
    }
    @Override
    public void execute() throws IOException{
        runnable.run();
    }

}
