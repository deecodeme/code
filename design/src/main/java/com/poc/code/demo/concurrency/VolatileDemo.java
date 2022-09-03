package com.poc.code.demo.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class VolatileDemo {
    private volatile int counter;

    public int incrementAndGet() {
        counter = counter + 1;
        return counter;
    }

    public int getValue() {
        return counter;
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        VolatileDemo counterService = new VolatileDemo();

        Runnable readTask1 = () -> {
            System.out.printf("%s : Counter value: %s\n", Thread.currentThread().getName(), counterService.getValue());
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
