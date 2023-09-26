package com.poc.code.practices.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CompletableFutureComposability {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        //executionTimeoutWithFutureGet();
        //executionTimeoutWithFutureGetCancelFuture();
        executionWithTimeout();
    }

    private static void executionTimeoutWithFutureGet() throws InterruptedException, ExecutionException {
        CompletableFuture<Void> completableFuture = CompletableFuture
            .supplyAsync(() -> "Hello")
            .thenApplyAsync(s -> s + " World")
            .thenApplyAsync(String::toUpperCase)
            .thenAccept(s -> {
                sleep(5000);
                System.out.println(s);
            });
        System.out.println("Continuing with other operations");
        try {
            // set a timeout for the future to complete
            completableFuture.get(3000, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            System.out.println("Timeout occurred");
            // Wait for the future to complete
            Thread.sleep(3000);
        }
    }

    private static void executionTimeoutWithFutureGetCancelFuture() throws InterruptedException, ExecutionException {
        CompletableFuture<Void> completableFuture = CompletableFuture
            .supplyAsync(() -> "Hello")
            .thenApplyAsync(s -> s + " World")
            .thenApplyAsync(String::toUpperCase)
            .thenAccept(s -> {
                sleep(5000);
                System.out.println(s);
            });
        System.out.println("Continuing with other operations");
        try {
            // set a timeout for the future to complete
            completableFuture.get(3000, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            System.out.println("Timeout occurred, cancelling the future");
            completableFuture.cancel(true);
            System.out.println("Wait to see if future is still running");
            Thread.sleep(3000);
        }
    }

    private static void executionWithTimeout() throws InterruptedException, ExecutionException {
        CompletableFuture<Void> completableFuture = CompletableFuture
            .supplyAsync(() -> "Hello")
            .thenApplyAsync(s -> s + " World")
            .thenApplyAsync(String::toUpperCase)
            .thenAccept(s -> {
                sleep(5000);
                System.out.println(s);
            });
        System.out.println("Continuing with other operations");
        completableFuture.orTimeout(3000, TimeUnit.MILLISECONDS).get();
    }

    private static void sleep(long duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
