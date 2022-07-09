package com.poc.code.demo.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class FutureAndCallbackDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> callable = () -> {
            System.out.println("Sleeping in future");
            Thread.sleep(1000);
            return "Hello from future";
        };

        Future<String> future = executorService.submit(callable);
        System.out.println("Performing other operations in present");
        IntStream.range(1, 10).forEach(k -> {
            System.out.println(k);
            System.out.println("Sleeping in present");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {

            }
        });
        while(true){
            if (future.isDone()){
                System.out.println(future.get());
                break;
            }else{
                System.out.println("Waiting for message from future");
                Thread.sleep(100);
            }
        }

        executorService.shutdown();
        executorService.awaitTermination(100, TimeUnit.SECONDS);
    }
}
