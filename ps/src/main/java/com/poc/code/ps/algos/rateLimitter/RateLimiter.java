package com.poc.code.ps.algos.rateLimitter;

public interface RateLimiter {
    boolean rateLimit(int customerId);
}
