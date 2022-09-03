package com.poc.code.design.chess.gameMatch;

import com.poc.code.design.chess.PlayRequest;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class IntermediateQueue extends PlayRequestQueue {
    private PriorityQueue<PlayRequest> priorityQueue;

    private static final Lock lock = new ReentrantLock();

    public IntermediateQueue() {
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