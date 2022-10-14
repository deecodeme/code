package com.poc.kafka.apache.kafka;

import com.poc.kafka.MessagePublisher;
import com.poc.kafka.apache.kafka.serializers.Customer;
import com.poc.kafka.apache.kafka.serializers.CustomerSerializer;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.function.BiConsumer;

class KafkaConnectProducerTest {
    Logger log = LoggerFactory.getLogger(KafkaConnectProducerTest.class);

    private final BiConsumer<RecordMetadata, Exception> producerCallbackConsumer = (metadata, e) -> {
        if (e != null) {
            log.error("Error while publishing. error: {}", e.getMessage());
        } else {
            log.info("successfully published, metadata: {}", metadata.toString());
        }
    };

    @Test
    void publish() throws IOException {
        MessagePublisher<String, String> kafkaConnectProducer = KafkaConnectProducer.fromProperties();
        kafkaConnectProducer.publishBlocking("test", "test message");
    }

    @Test
    void publishCustomSerializer() {
        MessagePublisher<String, Customer> kafkaConnectProducer = KafkaConnectProducer.withSerializers(new StringSerializer(), new CustomerSerializer());
        kafkaConnectProducer.publishBlocking("test", new Customer(1234, "deepak"));
    }

    @Test
    void publishWithCallback() throws IOException {
        MessagePublisher<String, String> kafkaConnectProducer = KafkaConnectProducer.fromProperties();
        kafkaConnectProducer.publishNonBlocking("test", "callback", producerCallbackConsumer);
    }
}