package com.example.MyWatchList.DataModels;

public class VideosModel {
    Videos[] results;

    public Videos[] getResults() {return results;}
    public void setResults(Videos[] results) {this.results = results;}

    public static class Videos {
        private String name;
        private String key;
        private String site;
        private String type;
        private boolean official;

        public String getName() {return name;}
        public void setName(String name) {this.name = name;}
        public String getKey() {return key;}
        public void setKey(String key) {this.key = key;}
        public String getSite() {return site;}
        public void setSite(String site) {this.site = site;}
        public String getType() {return type;}
        public void setType(String type) {this.type = type;}
        public boolean getOfficial() {return official;}
        public void setOfficial(boolean official) {this.official = official;}
    }
}
