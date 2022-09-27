package com.poc.code.demo.reflection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class ReflectionTestTest {

    @Test
    void reflection() throws NoSuchMethodException {
        ReflectionTest obj = new ReflectionTest();
        obj.reflection();
    }

    @Test
    void instanceUsingReflection() throws InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        ReflectionTest obj = new ReflectionTest();
        obj.instanceUsingReflection();
    }
}