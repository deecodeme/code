package com.poc.code.practices.demo.resilience.timeLimiter;

import com.poc.code.practices.Main;
import io.github.resilience4j.timelimiter.TimeLimiterRegistry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Main.class)
class SomeClassUsingTimeLimiterTest {
    @Value("${timeLimit.default}")
    private Duration timeLimit;
    @Autowired
    private SomeInterfaceUsingTimeLimiter someInterfaceUsingTimeLimiter;

    @Autowired
    private TimeLimiterRegistry timeLimiterRegistry;

    @Test
    void doSomethingAsync() throws InterruptedException {
        assertThatThrownBy(() -> someInterfaceUsingTimeLimiter.doSomethingAsync().join()).hasCauseInstanceOf(TimeoutException.class);

    }

    @Test
    void doSomethingAsyncUsingNoAnnotationBasedTimeLimiter() throws InterruptedException {
        assertThatThrownBy(() -> someInterfaceUsingTimeLimiter.doSomethingAsyncWithTimeLimit().join()).hasCauseInstanceOf(TimeoutException.class);
    }
}