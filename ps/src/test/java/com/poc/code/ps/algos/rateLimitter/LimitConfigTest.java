package com.poc.code.ps.algos.rateLimitter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LimitConfigTest {

    @Test
    void isAllowed() {
        LimitConfig limitConfig = LimitConfig.of(1, 1);
        Assertions.assertFalse(limitConfig.isAllowed(2));
        Assertions.assertTrue(limitConfig.isAllowed(0));
    }
}