package com.poc.kafka.spring;

import com.poc.kafka.MessagePublisher;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Map;
import java.util.function.BiConsumer;

public class KafkaMessagePublisher<K, V> implements MessagePublisher<K, V> {
    private KafkaTemplate<K, V> kafkaTemplate;

    public KafkaMessagePublisher(KafkaTemplate<K, V> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publishFireNForget(String topic, V message, Map<String, byte[]> headers) {
        this.kafkaTemplate.send(topic, message);
    }

    @Override
    public void publishBlocking(final String topic, final V message, Map<String, byte[]> headers) {
        this.kafkaTemplate.send(topic, message);
    }

    @Override
    public void publishNonBlocking(final String topic, final V message, Map<String, byte[]> headers, final BiConsumer<RecordMetadata, Exception> callback) {
        ListenableFuture<SendResult<K, V>> future = this.kafkaTemplate.send(topic, message);
    }
}