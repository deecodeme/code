package com.poc.code.practices.design.rateLimiter;

public interface RateLimiter {
    boolean createRateLimit(String key, int allowedCountPerSec);
}
