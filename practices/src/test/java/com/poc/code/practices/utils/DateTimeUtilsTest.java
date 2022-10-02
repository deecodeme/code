package com.poc.code.practices.utils;

import org.junit.jupiter.api.Test;

class DateTimeUtilsTest {

    @Test
    void getMinutesOfDay() {
        System.out.println("Minutes of the day:" + DateTimeUtils.getMinutesOfDay());
        System.out.println("Hour of the day: "+DateTimeUtils.getHourOfTheDay());
    }
}