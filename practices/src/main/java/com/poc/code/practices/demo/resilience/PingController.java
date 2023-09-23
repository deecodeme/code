package com.poc.code.practices.demo.resilience;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deecodeme/api/code/practices/v1/ping")
@Slf4j
public class PingController {
    @Autowired
    private Downstream downstream;

    @Autowired
    private DownstreamAnnotated downstreamAnnotated;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String ping(@RequestParam String type) {
        try {
            return downstream.callDownstreamUsingResilience();
        } catch (Exception ex) {
            log.error("Exception: {}", ex.getMessage());
            return "Failure";
        }
    }


}
