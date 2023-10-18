package com.poc.code.practices.demo.resilience.timeLimiter;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.timelimiter.TimeLimiterRegistry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Component
public class SomeClassUsingTimeLimiter implements SomeInterfaceUsingTimeLimiter {
    @Autowired
    private TimeLimiterRegistry timeLimiterRegistry;
    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);

    @Override
    @Bulkhead(name = "downstream", type = Bulkhead.Type.THREADPOOL)
    @TimeLimiter(name = "downstream")
    public CompletableFuture<String> doSomethingAsync() throws InterruptedException {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Test result";
        });
    }

    public CompletableFuture<String> doSomethingAsyncWithTimeLimit() {
        return timeLimiterRegistry.timeLimiter("downstream").executeCompletionStage(executorService,
            () -> CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "Test result";
            })).toCompletableFuture();
    }
}
