package com.poc.code.ps.algos.rateLimitter;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class SlidingLogRateLimiter implements RateLimiter {
    private Map<Integer, SlidingWindow> customerSlidingWindowMap;
    private LimitConfig limitConfig;

    public SlidingLogRateLimiter(LimitConfig limitConfig) {
        this.limitConfig = limitConfig;
        this.customerSlidingWindowMap = new HashMap<>();
    }

    @Override
    public boolean rateLimit(int customerId) {
        SlidingWindow slidingWindow = getSlidingWindow(customerId);
        return slidingWindow.isAllowed(
                c -> this.limitConfig.isAllowed(c),
                time -> time.isBefore(Instant.now().minusSeconds(limitConfig.getWindowSize())),
                Instant.now());
    }

    private SlidingWindow getSlidingWindow(int customerId) {
        SlidingWindow slidingWindow;
        if (this.customerSlidingWindowMap.containsKey(customerId)) {
            slidingWindow = this.customerSlidingWindowMap.get(customerId);
        } else {
            slidingWindow = ListBasedSlidingWindow.of();
            this.customerSlidingWindowMap.put(customerId, slidingWindow);
        }
        return slidingWindow;
    }
}
