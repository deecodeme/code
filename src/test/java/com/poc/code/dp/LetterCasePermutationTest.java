package com.poc.code.dp;

import com.poc.code.ps.dp.LetterCasePermutation;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

class LetterCasePermutationTest {
    @Test
    void letterCasePermutationTopDown() {
        LetterCasePermutation obj = new LetterCasePermutation();
        assertThat(obj.letterCasePermutationTopDown("a1b2"),
                Matchers.containsInAnyOrder("a1b2", "A1b2", "a1B2", "A1B2"));
        assertThat(obj.letterCasePermutationTopDown("3z4"),
                Matchers.containsInAnyOrder("3z4", "3Z4"));
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
        Assertions.assertArrayEquals(List.of("a1b2", "A1b2", "a1B2", "A1B2").toArray(),
                obj.letterCasePermutationBottomUP("a1b2").toArray());
        Assertions.assertArrayEquals(List.of("3z4", "3Z4").toArray(),
                obj.letterCasePermutationBottomUP("3z4").toArray());
    }

    @Test
    void getPrefix() {
    }
}