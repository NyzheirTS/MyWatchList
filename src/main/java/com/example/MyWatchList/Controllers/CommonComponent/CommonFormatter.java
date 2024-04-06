package com.example.MyWatchList.Controllers.CommonComponent;

public class CommonFormatter {
    private CommonFormatter(){
    }

    public static String formatMoney(int money) {
        return String.format("$ %,d USD", money);
    }

    public static String formateRuntime(int time){
        int hours = time/60;
        int minutes = time % 60;

        if (hours>0){return hours + "h " +minutes+"m";}
        return minutes + "m";
    }

}
