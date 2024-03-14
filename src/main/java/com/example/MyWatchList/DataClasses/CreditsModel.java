package com.example.MyWatchList.DataClasses;

public class CreditsModel {
    private Cast[] cast;
    public Cast[] getCast() {return cast;}
    public void setCast(Cast[] cast) {this.cast = cast;}

    public static class Cast {
        private int gender;
        private int id;
        private String name;
        private String character;
        private String profile_path;

        public int getGender() {return gender;}
        public void setGender(int gender) {this.gender = gender;}
        public int getId() {return id;}
        public void setId(int id) {this.id = id;}
        public String getName() {return name;}
        public void setName(String name) {this.name = name;}
        public String getCharacter() {return character;}
        public void setCharacter(String character) {this.character = character;}
        public String getProfile_path() {return profile_path;}
        public void setProfile_path(String profile_path) {this.profile_path = profile_path;}
    }
}
