package com.poc.kafka.apache.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.function.Consumer;

class KafkaConnectConsumerTest {
    private static final Logger log = LoggerFactory.getLogger(KafkaConnectConsumerTest.class);
    private static final String BOOTSTRAP_SERVERS_LOCAL = "localhost:9092";

    @Test
    void pollAsync() {
        MessageConsumer<String, String> consumer = KafkaConnectConsumer.Builder.INSTANCE
                .bootstrapServers(BOOTSTRAP_SERVERS_LOCAL)
                .groupId("KafkaConnectConsumerTest")
                .keySerializerClass(StringDeserializer.class)
                .valSerializerClass(StringDeserializer.class)
                .build();
        consumer.subscribe(List.of("test"));
        Consumer<ConsumerRecords<String, String>> recordsProcessor = crs -> {
            for (ConsumerRecord<String, String> cr : crs) {
                log.info("Received record: {}", cr.toString());
            }
        };
        consumer.pollAsync(recordsProcessor, Duration.ofSeconds(1));
    }
}