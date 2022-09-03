package com.poc.code.demo.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounterDemo {
    private final AtomicInteger counter = new AtomicInteger(1000);

    public int getValue(){
        return counter.get();
    }

    public void increment(){
        counter.incrementAndGet();
    }

    public static void main(String[] args) throws InterruptedException{
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        AtomicCounterDemo counterService = new AtomicCounterDemo();

        System.out.printf("Initial counter value: %s\n", counterService.getValue());
        for (int i = 1; i <= 100; i++) {
            executorService.submit(() -> counterService.increment());
        }
        executorService.shutdown();
        executorService.awaitTermination(60, TimeUnit.SECONDS);
        System.out.printf("Final counter value: %s\n", counterService.getValue());
    }
}
