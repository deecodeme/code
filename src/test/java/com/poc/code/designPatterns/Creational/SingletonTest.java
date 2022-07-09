package com.poc.code.designPatterns.Creational;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SingletonTest {
    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testSingletonCreation() {
        Singleton s1 = Singleton.getSingletonMethodSynchronized();
        Singleton s2 = Singleton.getSingletonMethodSynchronized();
        Assertions.assertEquals(s1, s2);

        Singleton s3 = Singleton.getSingletonBlockSynchronized();
        Singleton s4 = Singleton.getSingletonBlockSynchronized();
        Assertions.assertEquals(s3, s4);
    }
}