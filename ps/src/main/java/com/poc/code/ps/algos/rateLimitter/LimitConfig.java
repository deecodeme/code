package com.poc.code.ps.algos.rateLimitter;

public class LimitConfig {
    private Integer windowSize;
    private int limit;

    private LimitConfig(int windowSize, int limit) {
        this.windowSize = windowSize;
        this.limit = limit;
    }

    public static LimitConfig of(int windowSize, int limit) {
        return new LimitConfig(windowSize, limit);
    }

    public int getWindowSize() {
        return windowSize;
    }

    public int getLimit() {
        return limit;
    }

    public boolean isAllowed(int count) {
        return count < this.limit;
    }
}
