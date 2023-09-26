package com.poc.code.practices.demo.resilience;

import feign.Headers;
import feign.RequestLine;

public interface DownstreamService {
    @RequestLine("GET /hello")
    @Headers("Content-Type: application/json")
    String hello();
}
