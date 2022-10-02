package com.poc.code.practices.design.chess.gameMatch;

import com.poc.code.practices.design.chess.PlayRequest;

import java.util.Optional;
import java.util.PriorityQueue;
import java.util.concurrent.locks.Lock;

public abstract class PlayRequestQueue {
    abstract PriorityQueue<PlayRequest> getPriorityQueue();

    abstract Lock getLock();

    public boolean isEmpty() {
        boolean isEmpty = false;
        this.getLock().lock();
        try {
            isEmpty = this.getPriorityQueue().isEmpty();
        } finally {
            this.getLock().unlock();
        }
        return isEmpty;
    }

    public Optional<PlayRequest> pop() {
        Optional<PlayRequest> playRequest = Optional.empty();
        this.getLock().lock();
        try {
            if (!this.getPriorityQueue().isEmpty()) {
                playRequest = Optional.of(this.getPriorityQueue().poll());
            }
        } finally {
            this.getLock().unlock();
        }
        return playRequest;
    }

    public void push(PlayRequest playRequest) {
        this.getLock().lock();
        try {
            this.getPriorityQueue().add(playRequest);
        } finally {
            this.getLock().unlock();
        }
    }
}
