package com.poc.code.practices.demo.concurrency;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.IntStream;

class ForkJoinDemoTest {
    private final Logger log = LoggerFactory.getLogger(ForkJoinDemo.class);

    @Test
    void fibonacci() {
        int n = 10;
        int result = new ForkJoinDemo.FibonacciRecursiveTask(n).result();
        Assertions.assertEquals(55, result);
        log.info("Fibonacci of {} is {}", n, result);

        result = new ForkJoinDemo.FibonacciForkJoinTask(n).result();
        Assertions.assertEquals(55, result);
        log.info("Fibonacci of {} is {}", n, result);
    }

    @Test
    void forkNAdd(){
        int sum = new ForkJoinDemo.ForkJoinAdder(IntStream.range(1, 100).toArray()).compute();
        log.info("Sum of 1 to {}: {}", 100, sum);
        Assertions.assertEquals(101*50, sum);
    }
}