package com.poc.code.practices.effectiveJava.singleton;

public interface Singleton {
    default String ping() {
        return "pong";
    }
}
