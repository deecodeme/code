package com.poc.code.practices.demo.concurrency;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class CompletableFutureDemo {
    private final ExecutorService executorService;

    private static final CompletableFutureDemo INSTANCE = new CompletableFutureDemo();

    private CompletableFutureDemo() {
        executorService = Executors.newFixedThreadPool(5);
    }

    public static CompletableFutureDemo getInstance() {
        return INSTANCE;
    }

    public Future<String> asASimpleFuture() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        executorService.submit(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                log.error("Execution interrupted: {}", e.getMessage());
            }
            completableFuture.complete("Hello from future!");
        });
        return completableFuture;
    }
}
