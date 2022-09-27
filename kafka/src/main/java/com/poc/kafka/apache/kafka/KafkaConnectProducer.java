package com.poc.kafka.apache.kafka;

import com.poc.kafka.MessagePublisher;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.Future;
import java.util.function.BiConsumer;

public class KafkaConnectProducer implements MessagePublisher {
    private final Logger log = LoggerFactory.getLogger(KafkaConnectProducer.class);

    private final Producer<String, String> producer;

    public KafkaConnectProducer() {
        Map<String, Object> configProps = Map.ofEntries(
                Map.entry(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"),
                Map.entry(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class),
                Map.entry(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class)
        );
        this.producer = new KafkaProducer<String, String>(configProps);
    }

    @Override
    public void publish(String topic, String message) {
        try {
            Future<RecordMetadata> future = this.producer.send(new ProducerRecord<>(topic, message));
            future.get();
        } catch (Exception e) {
            log.error("Error while publishing. topic: {}, message: {}, error: {}", topic, message, e.getMessage());
        }
    }

    @Override
    public void publish(String topic, String message, BiConsumer<RecordMetadata, Exception> callback) {
        this.producer.send(new ProducerRecord<>(topic, message), callback::accept);
    }
}
