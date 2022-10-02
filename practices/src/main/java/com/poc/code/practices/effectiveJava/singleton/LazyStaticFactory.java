package com.poc.code.practices.effectiveJava.singleton;

import java.time.Instant;

public class LazyStaticFactory implements Singleton {
    private static LazyStaticFactory INSTANCE;
    private final transient Instant instanceCreatedAt;

    private LazyStaticFactory() {
        this.instanceCreatedAt = Instant.now();
    }

    public static LazyStaticFactory getInstance() {
        if (INSTANCE == null) {
            synchronized (LazyStaticFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LazyStaticFactory();
                }
            }
        }
        return INSTANCE;
    }

    public Instant getInstanceCreatedAt() {
        return instanceCreatedAt;
    }

    @Override
    public String toString() {
        return "LazyStaticFactory{" +
                "instanceCreatedAt=" + instanceCreatedAt +
                '}';
    }
}
