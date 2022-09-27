package com.poc.code.demo.concurrency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;
import java.util.stream.IntStream;

public class FutureAndCallbackDemo {
    private static final Logger log = LoggerFactory.getLogger(FutureAndCallbackDemo.class);

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Callable<String> callable = () -> {
            System.out.println("Sleeping in future");
            Thread.sleep(1000);
            return "Hello from future";
        };

        Future<String> future = executorService.submit(callable);
        if (future.isDone()) {
            log.info("Future is done. Result: {}", future.get());
        }

        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        executorService.execute(() -> {
            System.out.println("Sleeping in future");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            completableFuture.complete("Hello from future");
        });
        System.out.println(completableFuture.get());
        System.out.println("Performing other operations in present");
        IntStream.range(1, 10).forEach(k -> {
            System.out.println(k);
            System.out.println("Sleeping in present");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {

            }
        });
//        while (true) {
//            if (future.isDone()) {
//                System.out.println(future.get());
//                break;
//            } else {
//                System.out.println("Waiting for message from future");
//                Thread.sleep(100);
//            }
//        }

        executorService.shutdown();
        executorService.awaitTermination(100, TimeUnit.SECONDS);
    }
}
