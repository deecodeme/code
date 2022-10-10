package com.poc.kafka.spring;

import com.poc.kafka.MessagePublisher;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.function.BiConsumer;

public class KafkaMessagePublisher implements MessagePublisher {
    private KafkaTemplate<String, String> kafkaTemplate;

    public KafkaMessagePublisher(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publishFireNForget(String topic, String message) {
        this.kafkaTemplate.send(topic, message);
    }

    @Override
    public void publishBlocking(final String topic, final String message) {
        this.kafkaTemplate.send(topic, message);
    }

    @Override
    public void publishNonBlocking(final String topic, final String message, final BiConsumer<RecordMetadata, Exception> callback) {
        ListenableFuture<SendResult<String, String>> future = this.kafkaTemplate.send(topic, message);
    }
}
