package com.poc.kafka;

import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.function.BiConsumer;

public interface MessagePublisher {
    void publishFireNForget(String topic, String message);
    void publishBlocking(String topic, String message);

    /*
    Non-Blocking kafka send
    Callback gets called on the producer's main thread so the ordering of the callbacks should be same as that of the sends on the same partition
    Callbacks should be reasonably and non-blocking fast to not delay the message sends()
    If you want to do some blocking or time taking operation in the callback, please do so in another thread
     */
    void publishNonBlocking(String topic, String message, BiConsumer<RecordMetadata, Exception> callback);
}
