package com.poc.code.practices.effectiveJava.memoryManagement;

import org.junit.jupiter.api.Test;

class PreferTryWithResourceOverFinallyTest {
    private static final PreferTryWithResourceOverFinally obj = new PreferTryWithResourceOverFinally();

    @Test
    void tryWithResources() {
        obj.readFileTryWithResources("/tmp/delete_a");
    }


    @Test
    void copyFileTryWithResources() {
        obj.copyFileTryWithResources("/tmp/delete_a", "/tmp/delete_b");
    }

    @Test
    void copyFileWithTryFinally() {
        obj.copyFileWithTryFinally("/tmp/delete_a", "/tmp/delete_b");
    }
}