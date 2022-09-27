package com.poc.code.effectiveJava.builder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NutritionalFactsTest {
    @Test
    public void testBuilder() {
        NutritionalFacts nutritionalFacts = new NutritionalFacts.Builder(100, 2)
                .fat(1).carbohydrate(2).vitaminA(1).build();
        Assertions.assertEquals(1, nutritionalFacts.getFat());
    }
}