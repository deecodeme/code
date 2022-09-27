package com.poc.code.demo.reflection;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReflectionOnGenericsTest {

    @Test
    void reflectGenericTypes() {
        ReflectionOnGenerics obj = new ReflectionOnGenerics();
        obj.reflectGenericTypes();
    }
}