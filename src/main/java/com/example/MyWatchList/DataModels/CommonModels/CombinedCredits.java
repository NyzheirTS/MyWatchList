package com.example.MyWatchList.DataModels.CommonModels;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CombinedCredits {
    private Cast[] cast;
    private Crew[] crew;

    public Cast[] getCast() {return cast;}
    public Crew[] getCrew() {return crew;}

    public List<CC> getBoth() {
        List<CC> combinedCredits = new ArrayList<>();
        if (cast != null) {
            Collections.addAll(combinedCredits, cast);
        }
        if (crew != null) {
            Collections.addAll(combinedCredits, crew);
        }
        return combinedCredits;
    }


    public static class Cast implements CC{
        private int id;
        private String title;
        private String name;
        private String poster_path;
        private String release_date;
        private String character;
        private String media_type;
        private double vote_average;
        private String first_air_date;
        private String job;


        @Override
        public String getTitle() { return title != null ? title + media_type + String.format(" (%s) ", media_type) : name + String.format(" (%s) ", media_type);}
        @Override
        public int getId() {return id;}
        @Override
        public String getPoster_path() {return poster_path;}
        @Override
        public String getRelease_date() {return release_date != null ? release_date : first_air_date;}
        @Override
        public double getVote_average() {return vote_average;}
        @Override
        public String getMedia_type() {return media_type;}
        @Override
        public String getCharacter() {return character == null ? job : "Role: " + character;}
    }

    public static class Crew implements CC{
        private int id;
        private String poster_path;
        private String release_date;
        private String title;
        private String name;
        private double vote_average;
        private String department;
        private String job;
        private String media_type;
        private String first_air_date;
        private String character;



        @Override
        public String getTitle() { return title != null ? title : name;}
        @Override
        public int getId() {return id;}
        @Override
        public String getPoster_path() {return poster_path;}
        @Override
        public String getRelease_date() {return release_date != null ? release_date : first_air_date;}
        @Override
        public double getVote_average() {return vote_average;}
        @Override
        public String getMedia_type() {return media_type;}
        public String getDepartment() {return department;}
        @Override
        public String getCharacter() {return character == null ? "Job: " + job  : character;}

    }
}
