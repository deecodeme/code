package com.poc.code.practices.effectiveJava.builder.hierarchy;

import com.poc.code.practices.effectiveJava.builder.hierarchy.NyPizza;
import com.poc.code.practices.effectiveJava.builder.hierarchy.Pizza;
import org.junit.jupiter.api.Test;

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