package com.poc.code.practices.effectiveJava.builder.singleton;

import com.poc.code.practices.effectiveJava.singleton.UsingEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UsingEnumTest {

    @Test
    void sayHi() {
        Assertions.assertEquals("Hello!", UsingEnum.INSTANCE.sayHi());
    }

    @Test
    void ping() {
        Assertions.assertEquals("ping pong", UsingEnum.INSTANCE.ping());
    }
}