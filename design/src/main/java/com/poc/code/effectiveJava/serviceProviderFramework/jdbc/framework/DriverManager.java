package com.poc.code.effectiveJava.serviceProviderFramework.jdbc.framework;

import org.reflections.Reflections;

import java.util.Set;

public class DriverManager {
    public static void registerConnection() {

    }

    public static Connection getConnection(String DB) {
        Reflections reflections = new Reflections("com.poc.code.effectiveJava.serviceProviderFramework.jdbc.vendors");
        Set<Class<? extends Driver>> drivers = reflections.getSubTypesOf(Driver.class);
        throw new IllegalArgumentException("Not a valid url");
    }
}
