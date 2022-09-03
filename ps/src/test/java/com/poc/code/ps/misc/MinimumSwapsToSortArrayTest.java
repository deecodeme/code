package com.poc.code.ps.misc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MinimumSwapsToSortArrayTest {

    @Test
    void usingGraphCycles() {
        MinimumSwapsToSortArray obj = new MinimumSwapsToSortArray();
        Assertions.assertEquals(3, obj.usingGraphCycles(new int[]{2, 4, 5, 1, 3}));
        Assertions.assertEquals(1, obj.usingGraphCycles(new int[]{2, 8, 5, 4}));
    }
}