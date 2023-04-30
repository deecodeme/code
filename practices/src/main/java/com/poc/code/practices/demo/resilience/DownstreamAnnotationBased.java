package com.poc.code.practices.demo.resilience;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.feign.FeignDecorators;
import io.github.resilience4j.feign.Resilience4jFeign;
import io.github.resilience4j.retry.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class DownstreamAnnotationBased {
    public String callDownstreamFeignClient() throws IOException {
        log.info("Calling downstream using Feign client with circuit breaker");
        //create a circuit breaker using the above config
        CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("downstream");

        Retry retry = Retry.ofDefaults("downstream");

        FeignDecorators decorators = FeignDecorators.builder().withRetry(retry).build();
        //DownstreamService downstreamService = Feign.builder().target(DownstreamService.class, "http://localhost:8024");
        DownstreamService downstreamService = Resilience4jFeign.builder(decorators)
                .target(DownstreamService.class, "http://localhost:8024");
        return downstreamService.hello();
    }
}
