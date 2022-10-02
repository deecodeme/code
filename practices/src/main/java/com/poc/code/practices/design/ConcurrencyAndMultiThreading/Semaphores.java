package com.poc.code.practices.design.ConcurrencyAndMultiThreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

public class Semaphores {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        Semaphore semaphore = new Semaphore(5);

        Runnable task = () -> {
            boolean isAcquired = false;
            try{
                isAcquired = semaphore.tryAcquire(1);
                if (isAcquired){
                    System.out.println("Semaphore acquired");
                    Thread.sleep(10);
                }else{
                    System.out.println("Semaphore could not be acquired");
                }
            }catch (InterruptedException e){
                System.out.println("Exception: " + e.getMessage());
            }finally {
                if (isAcquired){
                    semaphore.release();
                }
            }
        };

        IntStream.range(1, 10).forEach(i -> executor.execute(task));
        executor.shutdown();
    }
}
