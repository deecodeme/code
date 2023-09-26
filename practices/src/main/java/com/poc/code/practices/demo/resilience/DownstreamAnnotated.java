package com.poc.code.practices.demo.resilience;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DownstreamAnnotated {
//    @Retry(name = "downstream", fallbackMethod = "callResilientAnnotatedDownstreamFallback")
//    public String callResilientAnnotatedDownstream(){
//        log.info("Calling downstream using Feign client with annotated resilience");
//        DownstreamService downstreamService = Feign.builder().target(DownstreamService.class, "http://localhost:8024");
//        return downstreamService.hello();
//    }
//
//    public String callResilientAnnotatedDownstreamFallback(Exception e) throws Exception {
//        log.info("Fallback......");
//        throw e;
//    }
}
