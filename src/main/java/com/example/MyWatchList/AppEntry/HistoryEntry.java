package com.example.MyWatchList.AppEntry;

public class HistoryEntry {
    private  String pagetype;
    private int nodeID;
    private String mediatype;

    public String getPagetype() {
        return pagetype;
    }

    public int getNodeID() {
        return nodeID;
    }

    public void setNodeID(int nodeID) {
        this.nodeID = nodeID;
    }

    public String getMediatype() {
        return mediatype;
    }

    public void setMediatype(String mediatype) {
        this.mediatype = mediatype;
    }
}
