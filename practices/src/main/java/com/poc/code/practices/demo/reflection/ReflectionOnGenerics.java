package com.poc.code.practices.demo.reflection;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class ReflectionOnGenerics {
    private static class Invoice {
        public int price;
        public int quantity;
    }

    private static class GenericClass {
        public List<Invoice> invoices;
    }

    public void reflectGenericTypes() {
        try {
            Type type = GenericClass.class.getDeclaredField("invoices").getGenericType();
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                for (Type t : parameterizedType.getActualTypeArguments()) {
                    System.out.printf("Actual type: %s\n", t.toString());
                }
            }
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

}
