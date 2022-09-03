package com.poc.code.utils;

import java.time.LocalDateTime;

public class DateTimeUtils {
    public static int getMinutesOfDay() {
        return LocalDateTime.now().getHour() * 60 + LocalDateTime.now().getMinute();
    }

    public static int getHourOfTheDay() {
        return LocalDateTime.now().getHour();
    }
}
