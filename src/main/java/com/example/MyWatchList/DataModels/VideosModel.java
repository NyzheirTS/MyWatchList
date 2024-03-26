package com.example.MyWatchList.DataModels;

public class VideosModel {
    Videos[] results;

    public Videos[] getResults() {return results;}

    public static class Videos {
        private String name;
        private String key;
        private String site;
        private String type;
        private boolean official;

        public String getName() {return name;}
        public String getKey() {return key;}
        public String getSite() {return site;}
        public String getType() {return type;}
        public boolean getOfficial() {return official;}
    }
}
