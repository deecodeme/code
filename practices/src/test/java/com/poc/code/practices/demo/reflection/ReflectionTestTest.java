package com.poc.code.practices.demo.reflection;

import com.poc.code.practices.demo.reflection.ReflectionTest;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

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