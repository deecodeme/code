package com.poc.code.practices.design.ConcurrencyAndMultiThreading.LockAndCondition;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChannelImpl implements Channel {
    private static final int CAPACITY = 10;
    private final Queue queue = new LinkedList();
    private final Random random = new Random();

    private final Lock lock = new ReentrantLock();
    private final Condition bufferNotFull = lock.newCondition();
    private final Condition bufferNotEmpty = lock.newCondition();

    @Override
    public void put(Object val) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == CAPACITY) {
                System.out.println("Buffer capacity full, awaiting...");
                bufferNotFull.await();
            }
            boolean isAdded = queue.offer(val);
            if (isAdded) {
                System.out.printf("%d Item added to channel successfully, thread: %s \n",
                    val, Thread.currentThread().getName());
                System.out.println(Thread.currentThread().getName() + " Signalling that, buffer is not empty anymore");
                bufferNotEmpty.signalAll();
            }

        } finally {
            lock.unlock();
        }
    }

    @Override
    public Object get() throws InterruptedException {
        Object val;
        lock.lock();
        try {
            while (queue.size() == 0) {
                System.out.println(Thread.currentThread().getName() + " Awaiting buffer to get filled with more data");
                bufferNotEmpty.await();
            }
            val = queue.poll();
            System.out.println(Thread.currentThread().getName() + " Removed " + val.toString() + " from channel");
            System.out.println(Thread.currentThread().getName() + " Signal to threads waiting on buffer to get empty");
            bufferNotFull.signalAll();
        } finally {
            lock.unlock();
        }
        return val;
    }
}
