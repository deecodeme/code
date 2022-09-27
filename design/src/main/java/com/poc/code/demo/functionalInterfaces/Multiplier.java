package com.poc.code.demo.functionalInterfaces;

import java.util.stream.IntStream;

@java.lang.FunctionalInterface
public interface Multiplier {
    int multiple();
}

class FunctionalInterfaceDemo {
    public int multiply(int num, Multiplier multiplier) {
        return num * multiplier.multiple();
    }

    public int randomNumbers() {
        return IntStream.range(1, 10).map(n -> multiply(n, () -> 2)).sum();
    }
}
