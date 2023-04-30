package com.poc.code.practices.demo.resilience;

import feign.Feign;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.decorators.Decorators;
import io.github.resilience4j.feign.FeignDecorators;
import io.github.resilience4j.feign.Resilience4jFeign;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Component
@Slf4j
public class Downstream {
    public String callDownstream() {
        log.info("Calling downstream");
        return "dummy call";
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

    private OkHttpClient withOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.SECONDS)
                .readTimeout(2, TimeUnit.SECONDS)
                .writeTimeout(2, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(5, 5, TimeUnit.MINUTES))
                .build();
    }

    public String callDownstreamUsingResilience() {
        log.info("Calling downstream using Resilience4J with retry");
        Retry retry = Retry.of("custom", RetryConfig.from(RetryConfig.ofDefaults()).maxAttempts(1).build());
        CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("custom");
        Supplier<String> hellloSupplier = () -> {
            DownstreamService downstreamService = Feign.builder()
                    .target(DownstreamService.class, "http://localhost:8024");
            return downstreamService.hello();
        };
        Supplier<String> decoratedSupplier = Decorators.ofSupplier(hellloSupplier)
                .withRetry(retry)
                .withCircuitBreaker(circuitBreaker)
                .decorate();
        String helloResponse = Try
                .ofSupplier(decoratedSupplier)
                .recover(Throwable.class, Throwable::getMessage)
                .get();
        log.info("Response: ", helloResponse);
        return helloResponse;
    }
}
