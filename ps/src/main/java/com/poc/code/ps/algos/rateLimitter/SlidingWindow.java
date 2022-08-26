package com.poc.code.ps.algos.rateLimitter;

import java.time.Instant;
import java.util.function.Predicate;

public interface SlidingWindow {
    public boolean isAllowed(Predicate<Integer> isLimitAllowed, Predicate<Instant> isExpired, Instant request);
}
