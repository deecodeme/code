package com.poc.code.strings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LetterCombinationOfPhoneNumberTest {
    private LetterCombinationOfPhoneNumber letterCombinationOfPhoneNumber;

    @BeforeEach
    public void setUp() {
        letterCombinationOfPhoneNumber = new LetterCombinationOfPhoneNumber();
    }

    @Test
    public void letterCombinations() {
        Assertions.assertArrayEquals(new String[]{"ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"},
                letterCombinationOfPhoneNumber.letterCombinations("23").toArray());
    }
}