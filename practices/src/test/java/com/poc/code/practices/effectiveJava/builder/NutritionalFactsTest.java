package com.poc.code.practices.effectiveJava.builder;

import com.poc.code.practices.effectiveJava.builder.NutritionalFacts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NutritionalFactsTest {
    @Test
    public void testBuilder() {
        NutritionalFacts nutritionalFacts = new NutritionalFacts.Builder(100, 2)
                .fat(1).carbohydrate(2).vitaminA(1).build();
        Assertions.assertEquals(1, nutritionalFacts.getFat());
    }
}