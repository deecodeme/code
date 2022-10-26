package com.poc.kafka.apache.kafka.interceptors;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class ProducerLoggingInterceptor<K, V> implements ProducerInterceptor<K, V> {
    private final Logger log = LoggerFactory.getLogger(ProducerLoggingInterceptor.class);

    @Override
    public ProducerRecord<K, V> onSend(ProducerRecord<K, V> record) {
        log.info("Record being published: {}", record.toString());
        return record;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        if (exception != null) {
            log.error("Exception on acknowledgement. exception: {}", exception.toString());
        } else {
            log.info("Record acknowledged. metadata: {}", metadata.toString());
        }
    }

    @Override
    public void close() {
        log.info("Closing interceptor");
    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
