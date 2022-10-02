package com.poc.code.practices.demo.reflection;

import com.poc.code.practices.demo.reflection.ReflectionOnGenerics;
import org.junit.jupiter.api.Test;

class ReflectionOnGenericsTest {

    @Test
    void reflectGenericTypes() {
        ReflectionOnGenerics obj = new ReflectionOnGenerics();
        obj.reflectGenericTypes();
    }
}