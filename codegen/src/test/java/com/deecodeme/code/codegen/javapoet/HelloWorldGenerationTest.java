package com.deecodeme.code.codegen.javapoet;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

class HelloWorldGenerationTest {

    @Test
    void generateHelloWorldClass() throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
        HelloWorldGeneration obj = new HelloWorldGeneration();
        obj.generateHelloWorldClass();
    }
}