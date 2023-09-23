package com.poc.code.practices.demo.resilience.timeLimiter;

import java.util.concurrent.CompletableFuture;

public interface SomeInterfaceUsingTimeLimiter {
    public CompletableFuture<String> doSomethingAsync() throws InterruptedException;

    CompletableFuture<String> doSomethingAsyncWithTimeLimit();
}
