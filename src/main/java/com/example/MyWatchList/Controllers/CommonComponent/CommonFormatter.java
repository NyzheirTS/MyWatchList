package com.example.MyWatchList.Controllers.CommonComponent;

public class CommonFormatter {
    private CommonFormatter(){
    }

    public static String formatMoney(long money) {
        return String.format(money <= 0 ?  "N/A" :"$ %,d USD", money);
    }

    public static String formateRuntime(int time){
        int hours = time/60;
        int minutes = time % 60;

        if (hours<0 && minutes < 0){ return "N/A";}
        else if (hours>0){return hours + "h " +minutes+"m";}
        return minutes + "m";
    }

}
