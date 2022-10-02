package com.poc.code.practices.demo.reflection.annotations;

import com.poc.code.practices.demo.reflection.annotations.DemoAnnotationReflection;
import org.junit.jupiter.api.Test;

class DemoAnnotationReflectionTest {

    @Test
    void checkIfAnnotationApplied() {
        DemoAnnotationReflection obj = new DemoAnnotationReflection();
        obj.checkIfAnnotationApplied();
    }
}