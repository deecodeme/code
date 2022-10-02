package com.poc.code.practices.design.rateLimiter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TokenBasedRateLimiter implements RateLimiter {
    private Logger log = Logger.getLogger(TokenBasedRateLimiter.class.getName());
    private final Map<String, Integer> token;
    private final Lock lock = new ReentrantLock();

    public TokenBasedRateLimiter() {
        this.token = new HashMap<>();
    }

    public boolean getToken(String key) {
        lock.lock();
        try {
            int tokens = this.token.getOrDefault(key, 0);
            if (tokens > 0) {
                this.token.put(key, tokens - 1);
                return true;
            } else {
                return false;
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean createRateLimit(String key, int allowedCountPerSec) throws IllegalArgumentException {
        lock.lock();
        boolean isDone = false;
        try {
            this.token.put(key, allowedCountPerSec);
            isDone = true;
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
        } finally {
            lock.unlock();
        }
        return isDone;
    }


}
