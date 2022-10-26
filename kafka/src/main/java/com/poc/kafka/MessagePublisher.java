package com.poc.kafka;

import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;
import java.util.function.BiConsumer;

public interface MessagePublisher<K, V> {
    void publishFireNForget(String topic, V message, Map<String, byte[]> headers);

    void publishBlocking(String topic, V message, Map<String, byte[]> headers);

    /*
    Non-Blocking kafka send
    Callback gets called on the producer's main thread so the ordering of the callbacks should be same as that of the sends on the same partition
    Callbacks should be reasonably and non-blocking fast to not delay the message sends()
    If you want to do some blocking or time taking operation in the callback, please do so in another thread
     */
    void publishNonBlocking(String topic, V message, Map<String, byte[]> headers, BiConsumer<RecordMetadata, Exception> callback);
}
