package com.poc.code.practices.demo.varHandle;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ArrayHandleTest {

    @Test
    void setArrayElement() {
        Assertions.assertEquals(105, ArrayHandle.setArrayElement(105));
    }


    @Test
    void compareAndSet() {
        Assertions.assertEquals(5, ArrayHandle.compareAndSet(5, 4, 5));
    }
}