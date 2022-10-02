package com.poc.code.practices.effectiveJava.singleton;

public enum UsingEnum implements Singleton {
    INSTANCE;

    public String sayHi() {
        return "Hello!";
    }

    @Override
    public String ping() {
        return "ping pong";
    }
}
