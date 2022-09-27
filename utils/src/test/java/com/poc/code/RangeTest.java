package com.poc.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RangeTest {

    @Test
    void isInclusive() {
        Range<Integer> range = new Range<>(1, 5);
        Assertions.assertTrue(range.isInclusive(1));
        Assertions.assertTrue(range.isInclusive(3));
        Assertions.assertTrue(range.isInclusive(5));
        Assertions.assertFalse(range.isInclusive(10));
        Assertions.assertFalse(range.isInclusive(-1));
    }
}