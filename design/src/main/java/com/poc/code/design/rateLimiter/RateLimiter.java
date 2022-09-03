package com.poc.code.design.rateLimiter;

public interface RateLimiter {
    boolean createRateLimit(String key, int allowedCountPerSec);
}
