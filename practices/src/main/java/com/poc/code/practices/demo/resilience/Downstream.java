package com.poc.code.practices.demo.resilience;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.decorators.Decorators;
import io.github.resilience4j.feign.FeignDecorators;
import io.github.resilience4j.feign.Resilience4jFeign;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.function.Supplier;

@Component
@Slf4j
public class Downstream {

    @Autowired
    private DownstreamService downstreamService;

    public String callDownstreamUsingResilience() {
        Retry retry = Retry.of("custom", RetryConfig.from(RetryConfig.ofDefaults()).maxAttempts(3).build());
//        CircuitBreaker circuitBreaker = CircuitBreaker.of("custom", CircuitBreakerConfig.custom()
//                        .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
//                        .minimumNumberOfCalls(1)
//                        .failureRateThreshold(5)
//                        .recordExceptions(IOException.class, Exception.class)
//                        .build());
        Supplier<String> hellloSupplier = () -> {
            log.info("Calling downstream using Resilience4J with retry");
            return downstreamService.hello();
        };
        Supplier<String> decoratedSupplier = Decorators.ofSupplier(hellloSupplier)
            .withRetry(retry)
//                .withCircuitBreaker(circuitBreaker)
            .decorate();
        String helloResponse = Try
            .ofSupplier(decoratedSupplier)
            .onSuccess(s -> log.info("Response: {}))", s))
            .onFailure(e -> log.error("Error: {}", e.getMessage()))
            .recover(Throwable.class, e -> "Downstream is not available")
            .get();
        return helloResponse;
    }

    public String callDownstreamFeignClient() throws IOException {
        log.info("Calling downstream using Feign client with circuit breaker");
        //create a circuit breaker using the above config
        CircuitBreaker circuitBreaker = CircuitBreaker.of("downstream", getCircuitBreakerConfig());

        Retry retry = Retry.ofDefaults("downstream");

        FeignDecorators decorators = FeignDecorators.builder().withRetry(retry).build();
        //DownstreamService downstreamService = Feign.builder().target(DownstreamService.class, "http://localhost:8024");
        DownstreamService downstreamService = Resilience4jFeign.builder(decorators)
            .target(DownstreamService.class, "http://localhost:8024");
        return downstreamService.hello();
    }

    private static CircuitBreakerConfig getCircuitBreakerConfig() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
            .failureRateThreshold(50)
            .waitDurationInOpenState(Duration.of(1, ChronoUnit.SECONDS))
            .slidingWindowSize(5)
            .build();
        return config;
    }
}
