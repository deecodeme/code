package com.poc.kafka.apache.kafka;

import com.poc.kafka.MessagePublisher;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.function.BiConsumer;

class KafkaConnectProducerTest {
    Logger log = LoggerFactory.getLogger(KafkaConnectProducerTest.class);
    private final MessagePublisher<String, String> kafkaConnectProducer = KafkaConnectProducer.fromProperties();

    private final BiConsumer<RecordMetadata, Exception> producerCallbackConsumer = (metadata, e) -> {
        if (e != null) {
            log.error("Error while publishing. error: {}", e.getMessage());
        } else {
            log.info("successfully published, metadata: {}", metadata);
        }
    };

    KafkaConnectProducerTest() throws IOException {
    }

    @Test
    void publish() {
        this.kafkaConnectProducer.publishBlocking("test", "test message");
    }

    @Test
    void publishWithCallback() {
        this.kafkaConnectProducer.publishNonBlocking("test", "test message with", producerCallbackConsumer);
    }
}