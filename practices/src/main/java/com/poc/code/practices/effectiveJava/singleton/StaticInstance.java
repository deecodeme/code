package com.poc.code.practices.effectiveJava.singleton;

public class StaticInstance implements Singleton {
    public static final StaticInstance INSTANCE = new StaticInstance();

    private StaticInstance() {
    }

    public String sayHi() {
        return "Hello!";
    }

    /*
    To use this instance while de-serializing
     */
    private Object readResolve() {
        return INSTANCE;
    }
}
