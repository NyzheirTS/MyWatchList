package com.example.MyWatchList.DataModels.CommonModels;

public class CreditsModel {
    private Cast[] cast;
    private Crew[] crew;
    public Cast[] getCast() {return cast;}
    public Crew[] getCrew() {return crew;}

    public static class Cast {
        private int id;
        private String name;
        private String character;
        private String profile_path;

        public int getId() {return id;}
        public String getName() {return name;}
        public String getCharacter() {return character;}
        public String getProfile_path() {return profile_path;}
    }

    public static class Crew {
        private int id;
        private String name;
        private String department;
        private String job;

        public int getId() {return id;}
        public String getName() {return name;}
        public String getDepartment() {return department;}
        public String getJob() {return job;}
    }
}
