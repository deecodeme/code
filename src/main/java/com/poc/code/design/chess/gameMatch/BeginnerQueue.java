package com.poc.code.design.chess.gameMatch;

import com.poc.code.design.chess.PlayRequest;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BeginnerQueue extends PlayRequestQueue {
    private PriorityQueue<PlayRequest> priorityQueue;
    private final Lock lock = new ReentrantLock();

    public BeginnerQueue() {
        this.priorityQueue = new PriorityQueue<PlayRequest>((o1, o2) -> o1.getRequestedAt().compareTo(o2.getRequestedAt()));
    }

    @Override
    PriorityQueue<PlayRequest> getPriorityQueue() {
        return this.priorityQueue;
    }

    @Override
    Lock getLock() {
        return lock;
    }
}