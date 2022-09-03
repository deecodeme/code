package com.poc.kafka.apache.kafka;

import com.poc.kafka.MessagePublisher;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Properties;

public class KafkaConnectProducer implements MessagePublisher {
    private final Logger log = LoggerFactory.getLogger(KafkaConnectProducer.class);

    private final Producer<String, String> producer;

    public KafkaConnectProducer() {
        Properties properties = new Properties();
        this.producer = new KafkaProducer<String, String>(properties);
    }

    @Override
    public void publish(String topic, String message) {

    }

    @Override
    public void publish(String topic, String message, ListenableFutureCallback callback) {
        log.info("Kafka publish topic: {}, message: {}", topic, message);
    }
}
