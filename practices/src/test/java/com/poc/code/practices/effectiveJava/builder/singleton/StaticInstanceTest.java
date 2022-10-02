package com.poc.code.practices.effectiveJava.builder.singleton;

import com.poc.code.practices.effectiveJava.singleton.StaticInstance;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StaticInstanceTest {

    @Test
    void sayHi() {
        Assertions.assertEquals("Hello!", StaticInstance.INSTANCE.sayHi());
    }
}