package com.example.MyWatchList.TestFolder.JUnitTest;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JunitFormatMoney {

    @Test
    public void testZeroDollars() {
        assertEquals("$ 0 USD", formatMoney(0));
    }

    @Test
    public void testOneDollar() {
        assertEquals("$ 1 USD", formatMoney(1));
    }

    @Test
    public void testNegativeAmount() {
        assertEquals("$ -1 USD", formatMoney(-1));
    }

    @Test
    public void testSmallAmount() {
        assertEquals("$ 999 USD", formatMoney(999));
    }

    @Test
    public void testOneThousandDollars() {assertEquals("$ 1,000 USD", formatMoney(1000));}

    @Test
    public void testLargeAmount() {
        assertEquals("$ 9,999,999 USD", formatMoney(9999999));
    }

    @Test
    public void testVeryLargeAmount() {
        assertEquals("$ 2,147,483,647 USD", formatMoney(Integer.MAX_VALUE));
    }

    @Test
    public void testNegativeOneThousand() {assertEquals("$ -1,000 USD", formatMoney(-1000));}

    @Test
    public void testVerySmallNegativeAmount() {
        assertEquals("$ -999 USD", formatMoney(-999));
    }

    @Test
    public void testVeryLargeNegativeAmount() {
        assertEquals("$ -2,147,483,648 USD", formatMoney(Integer.MIN_VALUE));
    }

    // The method being tested
    public static String formatMoney(int money) {
        return String.format("$ %,d USD", money);
    }
}
