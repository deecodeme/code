package com.poc.code.strings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DecodeTheOriginalStringTest {

    @Test
    void decodeString() {
        DecodeTheOriginalString obj = new DecodeTheOriginalString();
        String result = obj.decodeString("mnesi___ya__k____mime", 3);
        System.out.printf("Decoded string: %s \n", result);
        Assertions.assertTrue("my name is mike".equals(result));
    }

    @Test
    void decodeStringSimplified() {
        DecodeTheOriginalString obj = new DecodeTheOriginalString();
        String result = obj.decodeStringSimplified("mnesi___ya__k____mime", 3);
        System.out.printf("Decoded string: %s \n", result);
        Assertions.assertTrue("my name is mike".equals(result));
    }
}