package com.example.MyWatchList.Controllers.DynamicPages.TvInfoPages;

import com.example.MyWatchList.DataModels.CommonModels.MediaInfoPageModelDeserializer;
import com.example.MyWatchList.DataModels.TvModels.TvInfoPageModel;
import com.example.MyWatchList.TestFolder.TestJsonStringHolder;

import java.io.IOException;

public class TvInfoPageController {
    private static TvInfoPageController instance;
    private int nodeID;
    private String mediaType;
    private TvInfoPageModel model;



    public void update(int nodeid, String mediaType) throws IOException {
        this.nodeID = nodeid;
        this.mediaType = mediaType;
        model = MediaInfoPageModelDeserializer.fromJson(TestJsonStringHolder.getJsonStringTV(), TvInfoPageModel.class);
        buildTvPage(model);
    }

    private void buildTvPage(TvInfoPageModel jsonString){

    }


    public static void setInstance(TvInfoPageController controller) {
        TvInfoPageController.instance = controller;
    }

    public static TvInfoPageController getInstance(){
        return TvInfoPageController.instance;
    }
}
