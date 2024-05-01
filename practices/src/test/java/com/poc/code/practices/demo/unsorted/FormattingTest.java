/* (C)2024 */
package com.poc.code.practices.demo.unsorted;

import java.util.Locale;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class FormattingTest {
    @Test
    void timeFormattingTest() {
        log.info(
                "Default Locale, Time: {}",
                Formatting.localeSpecificTimeFormatting(Locale.getDefault()));
        log.info("US, Time: {}", Formatting.localeSpecificTimeFormatting(Locale.US));
        log.info("China Locale, Time: {}", Formatting.localeSpecificTimeFormatting(Locale.CHINA));
        log.info("France Locale, Time: {}", Formatting.localeSpecificTimeFormatting(Locale.FRANCE));
    }

    @Test
    void timeZoneFormattingTest() {
        log.info(
                "Default Locale, Zone: {}",
                Formatting.localeSpecificZoneFormatting(Locale.getDefault()));
        log.info("US, Zone: {}", Formatting.localeSpecificZoneFormatting(Locale.US));
        log.info("China Zone, Time: {}", Formatting.localeSpecificZoneFormatting(Locale.CHINA));
        log.info("France Zone, Time: {}", Formatting.localeSpecificZoneFormatting(Locale.FRANCE));
    }
}
