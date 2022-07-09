package com.poc.code.dp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LetterCasePermutationTest {
    @Test
    void letterCasePermutationTopDown() {
    }

    @Test
    void letterCasePermutationUtil() {
    }

    @Test
    void isASmallCase() {
    }

    @Test
    void isAUpperCase() {
    }

    @Test
    void letterCasePermutationBottomUP() {
        LetterCasePermutation obj = new LetterCasePermutation();
        /*
        ["a1b2","A1b2","a1B2","A1B2"]
        ["3z4","3Z4"]
         */
        Assertions.assertArrayEquals(List.of("a1b2", "A1b2", "a1B2", "A1B2").toArray(),
                obj.letterCasePermutationBottomUP("a1b1").toArray());
    }

    @Test
    void getPrefix() {
    }
}