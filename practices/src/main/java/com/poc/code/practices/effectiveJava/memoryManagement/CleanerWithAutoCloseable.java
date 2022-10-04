package com.poc.code.practices.effectiveJava.memoryManagement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.ref.Cleaner;

public class CleanerWithAutoCloseable {
    private static Logger log = LoggerFactory.getLogger(CleanerWithAutoCloseable.class.getName());

    // An auto closeable class using a cleaner as a safety net
    // Cleaner should not be required unless required
    public static class Room implements AutoCloseable {
        private static final Cleaner cleaner = Cleaner.create();

        // Resource that require cleaning
        private static class State implements Runnable {
            int numJunkPiles;

            public State(int numJunkPiles) {
                this.numJunkPiles = numJunkPiles;
            }

            @Override
            public void run() {
                log.info("Cleaning room");
                this.numJunkPiles = 0;
            }
        }

        private final State state;

        private final Cleaner.Cleanable cleanable;

        public Room(int numJunkPiles) {
            this.state = new State(numJunkPiles);
            this.cleanable = cleaner.register(this, state);
        }

        @Override
        public void close() throws Exception {
            this.cleanable.clean();
        }
    }

    public void cleanWithTryWithResource() {
        // This will auto clean, we do not have to wait for Cleaner
        try (Room room = new Room(7)) {
            log.info("Goodbye");
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
        }
    }

    public void waitForCleaner() {
        Room room = new Room(10);
        log.info("Peace Out");
    }

}
