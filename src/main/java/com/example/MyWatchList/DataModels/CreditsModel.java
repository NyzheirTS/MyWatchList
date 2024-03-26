package com.example.MyWatchList.DataModels;

public class CreditsModel {
    private Cast[] cast;
    public Cast[] getCast() {return cast;}

    public static class Cast {
        private int gender;
        private int id;
        private String name;
        private String character;
        private String profile_path;

        public int getGender() {return gender;}
        public int getId() {return id;}
        public String getName() {return name;}
        public String getCharacter() {return character;}
        public String getProfile_path() {return profile_path;}
    }
}
