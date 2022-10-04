package com.poc.code.practices.effectiveJava.memoryManagement;

import org.junit.jupiter.api.Test;

class CleanerWithAutoCloseableTest {

    @Test
    void cleanWithTryWithResource() {
        CleanerWithAutoCloseable obj = new CleanerWithAutoCloseable();
        obj.cleanWithTryWithResource();
    }

    @Test
    void waitForCleanerUnpredictable() {
        CleanerWithAutoCloseable obj = new CleanerWithAutoCloseable();
        obj.waitForCleaner();
        // This does not clean the room on my laptop, Cleaner is unpredictable.
        //Requesting System.gc, making the Cleaner run
        System.gc();
    }
}