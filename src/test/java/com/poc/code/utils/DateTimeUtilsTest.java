package com.poc.code.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeUtilsTest {

    @Test
    void getMinutesOfDay() {
        System.out.println("Minutes of the day:" + DateTimeUtils.getMinutesOfDay());
        System.out.println("Hour of the day: "+DateTimeUtils.getHourOfTheDay());
    }
}