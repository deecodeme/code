package com.poc.code.practices.effectiveJava.builder.singleton;

import com.poc.code.practices.effectiveJava.singleton.LazyStaticFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

class LazyStaticFactoryTest {

    @Test
    void getInstance() throws InterruptedException {
        List<LazyStaticFactory> instances = new ArrayList<>();
        Executor executor = Executors.newFixedThreadPool(5);
        IntStream.range(1, 5).forEach(i -> {
            executor.execute(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                instances.add(LazyStaticFactory.getInstance());
            });
        });
        Thread.sleep(1000);
        Instant expectedInstant = instances.get(0).getInstanceCreatedAt();
        instances.forEach(obj -> Assertions.assertEquals(expectedInstant, obj.getInstanceCreatedAt()));
    }
}