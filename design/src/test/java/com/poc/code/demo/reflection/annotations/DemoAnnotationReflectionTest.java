package com.poc.code.demo.reflection.annotations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DemoAnnotationReflectionTest {

    @Test
    void checkIfAnnotationApplied() {
        DemoAnnotationReflection obj = new DemoAnnotationReflection();
        obj.checkIfAnnotationApplied();
    }
}