package com.poc.kafka.apache.kafka.consumer;

import com.google.common.base.Strings;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class KafkaConnectConsumer<K, V> implements MessageConsumer<K, V> {
    private static final Logger log = LoggerFactory.getLogger(KafkaConnectConsumer.class);

    private transient boolean stopPolling = false;

    private KafkaConsumer<K, V> kafkaConsumer;

    private KafkaConnectConsumer(KafkaConsumer<K, V> kafkaConsumer) {
        this.kafkaConsumer = kafkaConsumer;
    }

    @Override
    public void subscribe(Collection<String> topics) {
        this.kafkaConsumer.subscribe(topics);
    }

    /*
    To be called once
     */
    @Override
    public void pollAsync(Consumer<ConsumerRecords<K, V>> consumerCallback, Duration timeout) {
        while (true) {
            ConsumerRecords<K, V> consumerRecords = this.kafkaConsumer.poll(timeout);
            log.info("Consumer records count: {}", consumerRecords.count());
            consumerCallback.accept(consumerRecords);
        }
    }

    public static class Builder<K, V> {
        public static final Builder INSTANCE = new Builder<>();
        private String bootstrapServers;
        private String groupId;
        private Class<Deserializer<K>> keyDeserializerClass;
        private Class<Deserializer<V>> valDeserializerClass;

        private Builder() {
        }

        public Builder<K, V> bootstrapServers(String bootstrapServers) {
            this.bootstrapServers = bootstrapServers;
            return this;
        }

        public Builder<K, V> groupId(final String groupId) {
            this.groupId = groupId;
            return this;
        }

        public Builder<K, V> keySerializerClass(Class<Deserializer<K>> keyDeserializerClass) {
            this.keyDeserializerClass = keyDeserializerClass;
            return this;
        }

        public Builder<K, V> valSerializerClass(Class<Deserializer<V>> valDeserializerClass) {
            this.valDeserializerClass = valDeserializerClass;
            return this;
        }

        public KafkaConnectConsumer<K, V> build() {
            Map<String, Object> configProps = new HashMap<>(Map.ofEntries(
                    Map.entry(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootstrapServers != null ? this.bootstrapServers : DEFAULT_BOOTSTRAP_SERVER),
                    Map.entry(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, this.keyDeserializerClass != null ? this.keyDeserializerClass : StringSerializer.class),
                    Map.entry(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, this.valDeserializerClass != null ? this.valDeserializerClass : StringSerializer.class)
            ));
            if (!Strings.isNullOrEmpty(groupId)) {
                configProps.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
            }
            return new KafkaConnectConsumer<>(new KafkaConsumer<>(configProps));
        }

    }
}
