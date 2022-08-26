package com.poc.code.ps.stack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class ExclusiveTimeOfFunctionsTest {
    private ExclusiveTimeOfFunctions exclusiveTimeOfFunctions;

    @BeforeEach
    public void setUp() {
        exclusiveTimeOfFunctions = new ExclusiveTimeOfFunctions();
    }

    @Test
    public void exclusiveTime() {
        Assertions.assertArrayEquals(new int[]{3, 4}, exclusiveTimeOfFunctions.exclusiveTime(2,
                Arrays.asList(new String[]{"0:start:0", "1:start:2", "1:end:5", "0:end:6"})));
        Assertions.assertArrayEquals(new int[]{8}, exclusiveTimeOfFunctions.exclusiveTime(1,
                Arrays.asList(new String[]{"0:start:0", "0:start:2", "0:end:5", "0:start:6", "0:end:6", "0:end:7"})));
        Assertions.assertArrayEquals(new int[]{7, 1}, exclusiveTimeOfFunctions.exclusiveTime(2,
                Arrays.asList(new String[]{"0:start:0", "0:start:2", "0:end:5", "1:start:6", "1:end:6", "0:end:7"})));
        Assertions.assertArrayEquals(new int[]{8, 1}, exclusiveTimeOfFunctions.exclusiveTime(2,
                Arrays.asList(new String[]{"0:start:0", "0:start:2", "0:end:5", "1:start:7", "1:end:7", "0:end:8"})));
        Assertions.assertArrayEquals(new int[]{1}, exclusiveTimeOfFunctions.exclusiveTime(1,
                Arrays.asList(new String[]{"0:start:0", "0:end:0"})));
        Assertions.assertArrayEquals(new int[]{6}, exclusiveTimeOfFunctions.exclusiveTime(1,
                Arrays.asList(new String[]{"0:start:0", "0:start:1", "0:start:2", "0:end:3", "0:end:4", "0:end:5"})));
    }
}