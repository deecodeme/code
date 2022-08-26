package com.poc.code.ps.algos.rateLimitter;

import java.time.Instant;
import java.util.LinkedList;
import java.util.function.Predicate;

public class ListBasedSlidingWindow implements SlidingWindow {
    private LinkedList<Instant> window;

    private ListBasedSlidingWindow() {
        this.window = new LinkedList<>();
    }

    public static ListBasedSlidingWindow of() {
        return new ListBasedSlidingWindow();
    }

    public boolean isAllowed(Predicate<Integer> isLimitAllowed, Predicate<Instant> isExpired, Instant request) {
        removeExpiredRequests(isExpired);
        if (isLimitAllowed.test(this.window.size())) {
            this.window.addFirst(request);
            return true;
        } else {
            return false;
        }
    }

    private void removeExpiredRequests(Predicate<Instant> isExpired) {
        while (this.window.size() > 0 && isExpired.test(this.window.getLast())) {
            this.window.removeLast();
        }
    }
}
