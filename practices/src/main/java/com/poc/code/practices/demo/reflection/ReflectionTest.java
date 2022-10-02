package com.poc.code.practices.demo.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ReflectionTest {
    public void reflection() throws NoSuchMethodException {
        try {
            Method[] methods = Object.class.getMethods();
            System.out.println("Object class methods");
            Arrays.stream(methods).forEach(method -> System.out.println(method.toString()));
            Method method = Object.class.getMethod("wait");
            Field[] fields = Integer.class.getFields();
            System.out.println("Integer class fields");
            Arrays.stream(fields).forEach(field -> System.out.println(field.toString()));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void instanceUsingReflection() {
        try {
            Constructor<String> constructor = String.class.getConstructor(String.class);
            String strObj = constructor.newInstance("Hello");
            Method method = String.class.getMethod("length");
            int len = (int) method.invoke(strObj);
            System.out.printf("String length %s \n", len);
        } catch (NoSuchMethodException |
                 InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
