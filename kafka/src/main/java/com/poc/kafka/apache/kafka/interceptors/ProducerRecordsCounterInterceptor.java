package com.poc.kafka.apache.kafka.interceptors;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ProducerRecordsCounterInterceptor<K, V> implements ProducerInterceptor<K, V> {
    private static final Logger log = LoggerFactory.getLogger(ProducerRecordsCounterInterceptor.class);
    private static final AtomicInteger sentCounter = new AtomicInteger(0);
    private static final AtomicInteger ackedCounter = new AtomicInteger(0);
    private static final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private static final Runnable countLogger = () -> {
        log.info("Records sent count: {}", sentCounter);
        log.info("Records acked count: {}", ackedCounter);
    };

    @Override
    public ProducerRecord<K, V> onSend(ProducerRecord<K, V> record) {
        sentCounter.incrementAndGet();
        return record;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        ackedCounter.incrementAndGet();
    }

    @Override
    public void close() {
        log.info("Closing interceptor: {}", this.getClass().getName());
        executorService.shutdown();
        log.info("Interceptor closed: {}", this.getClass().getName());
    }

    @Override
    public void configure(Map<String, ?> configs) {
        executorService.scheduleAtFixedRate(countLogger, 0, 10, TimeUnit.SECONDS);
    }
}
