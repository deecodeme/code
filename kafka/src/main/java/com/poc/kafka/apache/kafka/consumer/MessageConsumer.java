package com.poc.kafka.apache.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.time.Duration;
import java.util.Collection;
import java.util.function.Consumer;

public interface MessageConsumer<K, V> {
    String DEFAULT_BOOTSTRAP_SERVER = "localhost:9092";

    void subscribe(Collection<String> topics);

    /*
        To be called once
         */
    void pollAsync(Consumer<ConsumerRecords<K, V>> consumerCallback, Duration timeout);
}
