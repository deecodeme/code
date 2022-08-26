package com.poc.code.ps.algos.rateLimitter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SlidingLogRateLimiterTest {

    @Test
    void rateLimit() throws InterruptedException {
        SlidingLogRateLimiter rateLimiter = new SlidingLogRateLimiter(LimitConfig.of(1, 1));
        assertTrue(rateLimiter.rateLimit(1));
        assertTrue(rateLimiter.rateLimit(2));
        assertFalse(rateLimiter.rateLimit(2));
        assertFalse(rateLimiter.rateLimit(2));
        Thread.sleep(2000);
        assertTrue(rateLimiter.rateLimit(2));
    }
}