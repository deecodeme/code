package com.poc.kafka;

import org.springframework.util.concurrent.ListenableFutureCallback;

public interface MessagePublisher {
    void publish(String topic, String message);

    void publish(String topic, String message, ListenableFutureCallback callback);
}
