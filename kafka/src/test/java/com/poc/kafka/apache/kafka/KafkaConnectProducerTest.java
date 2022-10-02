package com.poc.kafka.apache.kafka;

import com.poc.kafka.MessagePublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class KafkaConnectProducerTest {
    Logger log = LoggerFactory.getLogger(KafkaConnectProducerTest.class);
    private final MessagePublisher kafkaConnectProducer = new KafkaConnectProducer();


    @Test
    void publish() {
        this.kafkaConnectProducer.publish("test", "test message");
    }

    @Test
    void publishWithCallback() {
        this.kafkaConnectProducer.publish("test", "test message", ((metadata, exception) -> {
            if (exception != null) {
                log.error("Error while publishing. error: {}", exception.getMessage());
            } else {
                log.info("successfully published, metadata: {}", metadata);
            }
        }));
    }
}