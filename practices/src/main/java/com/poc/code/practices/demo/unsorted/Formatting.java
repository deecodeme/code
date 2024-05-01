/* (C)2024 */
package com.poc.code.practices.demo.unsorted;

import java.util.Calendar;
import java.util.Formatter;
import java.util.Locale;

public class Formatting {
    public static String localeSpecificTimeFormatting(Locale locale) {
        Formatter formatter = new Formatter();
        return formatter.format(locale, "%tT", Calendar.getInstance()).toString();
    }

    public static String localeSpecificZoneFormatting(Locale locale) {
        Formatter formatter = new Formatter();
        return formatter.format(locale, "%tZ", Calendar.getInstance(locale)).toString();
    }
}
