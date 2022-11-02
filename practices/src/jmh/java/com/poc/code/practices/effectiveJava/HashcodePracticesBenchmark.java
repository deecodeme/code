package com.poc.code.practices.effectiveJava;

import com.poc.code.practices.effectiveJava.commonMethods.HashcodePractices;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;

public class HashcodePracticesBenchmark {
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public int hashcodeFromObjects() {
        HashcodePractices.PhoneNumberWithHashcode obj = new HashcodePractices.PhoneNumberWithHashcode(1, 876, 90);
        return obj.hashCodeFromObjects();
    }
}
