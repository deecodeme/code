package com.poc.code.demo.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    private int counter;

    private final ReentrantLock lock = new ReentrantLock();

    public int incrementAndGet() {
        try {
            System.out.printf("Is locked: %s : isHeldByCurrentThread: %s\n", lock.isLocked(), lock.isHeldByCurrentThread());
            if (lock.tryLock(400, TimeUnit.MILLISECONDS)) {
                try {
                    Thread.sleep(50);
                    counter = counter + 1;
                    return counter;
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("Could not acquire lock");
                return -1;
            }
        } catch (InterruptedException e) {
            System.out.printf("%s: InterruptedException waiting to acquire lock\n", Thread.currentThread().getName());
        }
        return -1;
    }

    public int getCount() {
        return counter;
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        ReentrantLockDemo counterService = new ReentrantLockDemo();

        Runnable readTask1 = () -> {
            System.out.printf("%s : Counter value: %s\n", Thread.currentThread().getName(), counterService.getCount());
        };

        Runnable writeTask1 = () -> {
            System.out.printf("%s : Write Task 1 : Counter value: %s\n", Thread.currentThread().getName(), counterService.incrementAndGet());
        };

        Runnable writeTask2 = () -> {
            System.out.printf("%s : Write Task 2 : Counter value: %s\n", Thread.currentThread().getName(), counterService.incrementAndGet());
        };

        boolean readFlag = false;
        for (int i = 0; i < 100; i++) {
            if (readFlag) {
                executorService.submit(readTask1);
                readFlag = false;
            } else {
                executorService.submit(writeTask1);
                executorService.submit(writeTask2);
                readFlag = true;
            }
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
    }
}
