package com.poc.code.practices.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class SortingUtilsTest {

    @Test
    void sortArrayAOnArrayB() {
        Assertions.assertIterableEquals(Arrays.asList(1, 2, 3, 4),
                SortingUtils.sortArrayAOnArrayB(Arrays.asList(4, 3, 2, 1), Arrays.asList(8, 7, 5, 4)));
        Assertions.assertIterableEquals(Arrays.asList(9, 2, 3, 4),
                SortingUtils.sortArrayAOnArrayB(Arrays.asList(4, 3, 2, 9), Arrays.asList(8, 7, 5, 4)));
    }
}