package com.example.MyWatchList.DataModels.CommonModels;

public class CombinedCredits {
    private Cast[] cast;
    private Crew[] crew;

    public Cast[] getCast() {return cast;}
    public Crew[] getCrew() {return crew;}

    public static class Cast {
        private int id;
        private String title;
        private String name;
        private String poster_path;
        private String release_date;
        private String character;
        private String media_type;
        private double vote_average;
        private int episode_count;

        public String getTitle() { return title != null ? title : name;}
        public int getId() {return id;}
        public String getPoster_path() {return poster_path;}
        public String getRelease_date() {return release_date;}
        public String getCharacter() {return character;}
        public String getMedia_type() {return media_type;}
        public double getVote_average() {return vote_average;}
        public int getEpisode_count() {return episode_count;}
    }

    public static class Crew {
        private int id;
        private String poster_path;
        private String release_date;
        private String title;
        private String name;
        private double vote_average;
        private String department;
        private String job;
        private String media_type;

        public String getTitle() { return title != null ? title : name;}
        public int getId() {return id;}
        public String getPoster_path() {return poster_path;}
        public String getRelease_date() {return release_date;}
        public double getVote_average() {return vote_average;}
        public String getDepartment() {return department;}
        public String getJob() {return job;}
        public String getMedia_type() {return media_type;}
    }
}
