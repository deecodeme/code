package com.deecodeme.code.codegen.javapoet;

import org.junit.jupiter.api.Test;

class HelloWorldGenerationTest {

    @Test
    void generateHelloWorldClass() {
        HelloWorldGeneration obj = new HelloWorldGeneration();
        obj.generateHelloWorldClass();
    }
}
