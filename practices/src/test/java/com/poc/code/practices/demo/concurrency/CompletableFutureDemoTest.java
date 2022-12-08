package com.poc.code.practices.demo.concurrency;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
class CompletableFutureDemoTest {
    private CompletableFutureDemo completableFutureDemo;

    @BeforeEach
    void setUp() {
        completableFutureDemo = CompletableFutureDemo.getInstance();
    }

    @Test
    void asASimpleFuture() {
        Future<String> future = completableFutureDemo.asASimpleFuture();
        try {
            String valueFromFuture = future.get();
            Assertions.assertTrue("Hello from future!".equals(valueFromFuture));
        } catch (ExecutionException | InterruptedException e) {
            log.error("Unable to get value from future: {}", e.getMessage());
        }
    }

    @Test
    void cancelFuture() {
        Future<String> future = completableFutureDemo.asASimpleFuture();
        future.cancel(true);
        Assertions.assertTrue(future.isCancelled());
    }

    @Test
    void supplyAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            return "Hello";
        });
        Assertions.assertEquals("Hello", completableFuture.get());
    }

    @Test
    void supplyAsyncCompose() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> "Hello")
                .thenApply(s -> {
                    log.info("There: {}", Thread.currentThread().getName());
                    return s + " There";
                })
                .thenApply((s) -> {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    log.info("I am from future: {}", Thread.currentThread().getName());
                    return s + " I am from future";
                })
                .thenApply((s) -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    log.info("How about you?: {}", Thread.currentThread().getName());
                    return s + " How about you?";
                }).thenAccept(log::info);
//        log.info(completableFuture.get());
    }
}