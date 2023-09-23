package com.poc.code.practices.demo.resilience.timeLimiter;

import com.poc.code.practices.Main;
import io.github.resilience4j.timelimiter.TimeLimiterRegistry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.TimeoutException;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Main.class)
class SomeClassUsingTimeLimiterTest {
    @Autowired
    private SomeInterfaceUsingTimeLimiter someInterfaceUsingTimeLimiter;

    @Autowired
    private TimeLimiterRegistry timeLimiterRegistry;

    @Test
    void doSomethingAsync() throws InterruptedException {
        Assertions.assertThrows(TimeoutException.class, () -> someInterfaceUsingTimeLimiter.doSomethingAsync().join());
    }

    @Test
    void doSomethingAsyncUsingNoAnnotationBasedTimeLimiter() throws InterruptedException {
        Assertions.assertThrows(TimeoutException.class, () -> someInterfaceUsingTimeLimiter.doSomethingAsyncWithTimeLimit().join());
    }
}