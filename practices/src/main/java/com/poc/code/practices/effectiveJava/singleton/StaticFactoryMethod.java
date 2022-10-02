package com.poc.code.practices.effectiveJava.singleton;

import java.io.Serializable;

public class StaticFactoryMethod implements Singleton, Serializable {
    private static final StaticFactoryMethod INSTANCE = new StaticFactoryMethod();

    private StaticFactoryMethod() {
    }

    public static StaticFactoryMethod getInstance() {
        return INSTANCE;
    }

    public String sayHi() {
        return "Hello!";
    }
}
