package com.poc.code.practices.demo.concurrency;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.BDDAssertions.then;

class FutureAndCallbackDemoTest {
    private final ExecutorService executorService = Executors.newFixedThreadPool(1);

    @Test
    public void completableFutureJoinIsABlockingOperation() {
        // given
        long firstFutureRunningTime = 5000;
        long secondFutureRunningTime = 3000;
        CompletableFuture<String> firstCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(firstFutureRunningTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Hello from first completable future";
        }, executorService);
        CompletableFuture<String> secondCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(secondFutureRunningTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Hello from second completable future";
        }, executorService);

        // when
        Instant timeBeforeJoinOperation = Instant.now();
        firstCompletableFuture.thenCombineAsync(secondCompletableFuture, (v1, v2) -> v1 + ", "+v2).join();
        Instant timeAfterJoinOperation = Instant.now();
        System.out.println(
            "Time taken to join in millis: " + timeAfterJoinOperation.minusMillis(timeBeforeJoinOperation.toEpochMilli()).toEpochMilli());

        // then
        then(timeAfterJoinOperation.isAfter(timeBeforeJoinOperation.plusMillis(firstFutureRunningTime + secondFutureRunningTime)));
    }

    @Test
    public void futureGetIsABlockingOperation() throws ExecutionException, InterruptedException {
        // given
        long futureRunningTime = 5000;
        Future<String> completableFuture = executorService.submit(() -> {
            try {
                Thread.sleep(futureRunningTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Hello from completable future";
        });

        // when
        Instant timeBeforeJoinOperation = Instant.now();
        completableFuture.get();
        Instant timeAfterJoinOperation = Instant.now();
        System.out.println(
            "Time taken to join in millis: " + timeAfterJoinOperation.minusMillis(timeBeforeJoinOperation.toEpochMilli()).toEpochMilli());

        // then
        then(timeAfterJoinOperation.isAfter(timeBeforeJoinOperation.plusMillis(futureRunningTime)));
    }

    @Test
    public void completableFutureJoinOrTimeoutMethodDoesNotCancelTheFuture() throws InterruptedException {
        // given
        long futureRunningTime = 10000;
        AtomicReference<Instant> time = new AtomicReference<>(Instant.now());
        Instant startTime = Instant.now();
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Instant timeIncrementer = Instant.now();
                Instant endTime = Instant.now().plusMillis(futureRunningTime);
                while (timeIncrementer.isBefore(endTime)) {
                    if(Thread.currentThread().isInterrupted()) {
                        return "Thread interrupted, breaking the loop";
                    }
                    timeIncrementer = timeIncrementer.plusMillis(10);
                    time.set(timeIncrementer);
                    System.out.println("Time incrementer running, time spent: " + Duration.between(startTime, timeIncrementer).toSeconds());
                }
                Thread.sleep(futureRunningTime);
                return "Hello from completable future";
            } catch (InterruptedException e) {
                System.out.println("Completable future interrupted" + e.getMessage());
                return "interrupted";
            }
        }, executorService);

        // when
        long operationTimeoutLimit = 5000;
        Instant timeBeforeJoinOperation = Instant.now();
        try {
            completableFuture.orTimeout(operationTimeoutLimit, TimeUnit.MILLISECONDS).join();
        } catch (CompletionException e) {
            if (e.getCause() instanceof TimeoutException) {
                System.out.println("Timeout exception thrown, cancelling the completable future");
                completableFuture.cancel(true);
            }
        }
        Instant timeAfterJoinOperation = Instant.now();
        System.out.println("Total time recorded: " + Duration.between(startTime, time.get()).toSeconds());
        Thread.sleep(2000);
        System.out.println("Total time recorded: " + Duration.between(startTime, time.get()).toSeconds());

        // then
        then(timeAfterJoinOperation.isAfter(timeBeforeJoinOperation.plusMillis(operationTimeoutLimit)));
        then(timeAfterJoinOperation.isBefore(timeBeforeJoinOperation.plusMillis(operationTimeoutLimit + 500)));

        then(completableFuture.isCancelled()).isFalse();
        then(completableFuture.isCompletedExceptionally()).isTrue();
    }

    @Test
    public void futureGetWithTimeoutMethodDoesNotCancelTheFuture() throws InterruptedException, ExecutionException, TimeoutException {
        // given
        long futureRunningTime = 12000;
        AtomicReference<Instant> time = new AtomicReference<>(Instant.now());
        Instant startTime = Instant.now();
        Future<String> future = executorService.submit(() -> {
            try {
                Instant timeIncrementer = Instant.now();
                Instant endTime = Instant.now().plusMillis(futureRunningTime);
                while (timeIncrementer.isBefore(endTime)) {
                    if(Thread.currentThread().isInterrupted()) {
                        return "Thread interrupted, breaking the loop";
                    }
                    timeIncrementer = timeIncrementer.plusMillis(10);
                    time.set(timeIncrementer);
                    System.out.println("Time incrementer running, time spent: " + Duration.between(startTime, timeIncrementer).toSeconds());
                }
                Thread.sleep(futureRunningTime);
                return "Hello from completable future";
            } catch (InterruptedException e) {
                System.out.println("Completable future interrupted" + e.getMessage());
                return "interrupted";
            }
        });

        // when
        long operationTimeoutLimit = 5000;
        Instant timeBeforeJoinOperation = Instant.now();
        try {
            future.get(operationTimeoutLimit, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            System.out.println("Timeout exception thrown, cancelling the future");
            future.cancel(true);
        }
        Instant timeAfterJoinOperation = Instant.now();
        System.out.println("Total time recorded: " + Duration.between(startTime, time.get()).toSeconds());
        Thread.sleep(2000);
        System.out.println("Total time recorded: " + Duration.between(startTime, time.get()).toSeconds());
        System.out.println(future.get());

        // then
        then(timeAfterJoinOperation.isAfter(timeBeforeJoinOperation.plusMillis(operationTimeoutLimit)));
        then(timeAfterJoinOperation.isBefore(timeBeforeJoinOperation.plusMillis(operationTimeoutLimit + 500)));

        then(future.isCancelled()).isTrue();
    }

}