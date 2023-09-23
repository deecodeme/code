package com.poc.code.practices.demo.resilience;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DownstreamFeignConfig {
    @Bean
    public DownstreamService downstreamService() {
        return Feign.builder()
            .decoder(new JacksonDecoder())
            .encoder(new JacksonEncoder())
            .target(DownstreamService.class, "http://localhost:8080");
    }
}
