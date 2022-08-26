package com.poc.code.demo.functionalInterfaces;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

class FunctionalInterfaceDemoTest {

    @Test
    void testSupplier() {
        Supplier<String> helloStringSupplier = () -> "Hello! supplier";
        Supplier<Instant> currentTimeSupplier = Instant::now;
    }

    @Test
    void testConsumer() {
        Consumer<String> helloStringConsumer = System.out::println;
        BiConsumer<String, String> helloBiConsumer = (s1, s2) -> System.out.printf("%s : %s%n", s1, s2);
        helloStringConsumer.accept("Hello! consumer");
        helloBiConsumer.accept("Hello! consumer1", "Hello! consumer2");
    }

    @Test
    void testFunctionalInterface() {
        Function<String, String> toUpperCase = String::toUpperCase;
        Function<String, String> toLowerCase = String::toLowerCase;
        Function<Integer, Double> log10MR = Math::log10;

        String s = "deepak";
        Assertions.assertEquals(s.toLowerCase(), toLowerCase.andThen(toLowerCase).apply(s));
        Assertions.assertEquals(s.toUpperCase(), toUpperCase.compose(toLowerCase).apply(s));
    }

}