package com.example.MyWatchList.TestFolder.JUnitTest;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JunitTestsFormateTime {
    @Test
    public void testZeroMinutes() {assertEquals("0m", formateRuntime(0));}

    @Test
    public void testLessThanOneHour() {assertEquals("45m", formateRuntime(45));}

    @Test
    public void testExactlyOneHour() {assertEquals("1h 0m", formateRuntime(60));}

    @Test
    public void testMoreThanOneHour() {assertEquals("1h 15m", formateRuntime(75));}

    @Test
    public void testMultipleHours() {assertEquals("4h 0m", formateRuntime(240));}

    @Test
    public void testLargeInput() {assertEquals("24h 0m", formateRuntime(1440));}

    @Test
    public void testOneMinuteLessThanOneHour() {assertEquals("59m", formateRuntime(59));}

    @Test
    public void testOneMinuteMoreThanOneHour() {assertEquals("1h 1m", formateRuntime(61));}

    @Test
    public void testEdgeCaseOneMinute() {assertEquals("1m", formateRuntime(1));}

    @Test
    public void testOneDayAndAHalf() {assertEquals("36h 0m", formateRuntime(2160));}

    @Test
    public void testVeryLargeInput() {assertEquals("1000h 0m", formateRuntime(60000));}

    //The method being tested
    public static String formateRuntime(int time){
        int hours = time/60;
        int minutes = time % 60;

        if (hours>0){return hours + "h " +minutes+"m";}
        return minutes + "m";
    }
}

