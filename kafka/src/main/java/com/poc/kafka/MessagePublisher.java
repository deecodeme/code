package com.poc.kafka;

import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.function.BiConsumer;

public interface MessagePublisher {
    void publish(String topic, String message);

    void publish(String topic, String message, BiConsumer<RecordMetadata, Exception> callback);
}
