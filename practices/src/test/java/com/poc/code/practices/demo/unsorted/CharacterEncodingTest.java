/* (C)2024 */
package com.poc.code.practices.demo.unsorted;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class CharacterEncodingTest {

    private static final Logger log = LoggerFactory.getLogger(CharacterEncodingTest.class);

    @Test
    void testCharacterEncoding() {
        char ch = 'A';
        log.info("ASCII Encoding of {} is {}", ch, toHexArray(CharacterEncoding.toAscii(ch)));
        log.info("UTF-8 Encoding of {} is {}", ch, toHexArray(CharacterEncoding.toUTF_8(ch)));
        log.info("UTF-16 Encoding of {} is {}", ch, toHexArray(CharacterEncoding.toUTF_16(ch)));
        log.info("UTF-16LE Encoding of {} is {}", ch, toHexArray(CharacterEncoding.toUTF_16LE(ch)));
    }

    private String toHexArray(byte[] b) {
        return IntStream.rangeClosed(0, b.length - 1)
                .mapToObj(bIndex -> String.format("0x%04X", b[bIndex]))
                .collect(Collectors.joining(",", "[", "]"));
    }
}
