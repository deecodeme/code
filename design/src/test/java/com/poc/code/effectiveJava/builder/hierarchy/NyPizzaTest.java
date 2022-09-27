package com.poc.code.effectiveJava.builder.hierarchy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NyPizzaTest {
    @Test
    public void createPizza() {
        NyPizza pizza = new NyPizza.Builder(NyPizza.Size.MEDIUM)
                .addTopping(Pizza.Topping.CAPCICUM)
                .addTopping(Pizza.Topping.MUSHROOM)
                .build();
        System.out.println(pizza.toString());
    }
}