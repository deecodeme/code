package com.poc.code.practices.effectiveJava.builder.singleton;

import com.poc.code.practices.effectiveJava.singleton.StaticFactoryMethod;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StaticFactoryMethodTest {

    @Test
    void sayHi() {
        Assertions.assertEquals("Hello!", StaticFactoryMethod.getInstance().sayHi());
    }
}