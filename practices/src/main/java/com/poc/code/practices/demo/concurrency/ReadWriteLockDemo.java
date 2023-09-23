package com.poc.code.practices.demo.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private int count = 0;

    public int incrementAndGet() {
        if (readWriteLock.writeLock().tryLock()) {
            try {
                count = count + 1;
                return count;
            } finally {
                readWriteLock.writeLock().unlock();
            }
        } else {
            System.out.println("could not acquire a write lock ");
            return -1;
        }
    }

    public int getValue() {
        if (readWriteLock.readLock().tryLock()) {
            try {
                return count;
            } finally {
                readWriteLock.readLock().unlock();
            }
        } else {
            System.out.println("could not acquire a read lock ");
            return -1;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReadWriteLockDemo counterService = new ReadWriteLockDemo();
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Runnable readTask = () -> {
            System.out.printf("%s : read task : counter value : %s \n", Thread.currentThread().getName(),
                counterService.getValue());
        };

        Runnable writeTask1 = () -> {
            System.out.printf("%s : write task1 : counter value after increase: %s \n", Thread.currentThread().getName(),
                counterService.incrementAndGet());
        };

        Runnable writeTask2 = () -> {
            System.out.printf("%s : write task2 : counter value after increase: %s \n", Thread.currentThread().getName(),
                counterService.incrementAndGet());
        };

        boolean read = false;
        for (int i = 1; i <= 20; i++) {
            if (read) {
                executorService.submit(readTask);
                read = false;
            } else {
                executorService.submit(writeTask1);
                executorService.submit(writeTask2);
                read = true;
            }
        }

        executorService.shutdown();
        executorService.awaitTermination(60, TimeUnit.SECONDS);
    }
}
