package com.poc.code.practices.designPatterns.Creational;

public class Singleton {
    public static final Singleton s = new Singleton();
    private static Singleton singleton;

    private Singleton() {
        System.out.println("Singleton object created");
    }

    public synchronized static Singleton getSingletonMethodSynchronized() {
        if (singleton == null) {
            System.out.println("Creating a new singleton object");
            singleton = new Singleton();
        }
        return singleton;
    }

    public static Singleton getSingletonBlockSynchronized() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
