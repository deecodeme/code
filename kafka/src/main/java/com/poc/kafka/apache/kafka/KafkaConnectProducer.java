package com.poc.kafka.apache.kafka;

import com.poc.kafka.MessagePublisher;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.Future;
import java.util.function.BiConsumer;

public class KafkaConnectProducer<K, V> implements MessagePublisher<K, V> {
    private static final String PRODUCER_CONFIG_PREFIX = "kafka.producer.";
    public static final String DEFAULT_BOOTSTRAP_SERVER = "localhost:9092";
    private final Logger log = LoggerFactory.getLogger(KafkaConnectProducer.class);

    private final Producer<K, V> producer;

    private KafkaConnectProducer(Producer<K, V> producer) {
        this.producer = producer;
    }

    public static class Builder<K, V> {
        public static final Builder INSTANCE = new Builder<>();
        private String bootstrapServers;
        private Class<Serializer<K>> keySerializerClass;
        private Class<Serializer<V>> valSerializerClass;
        private Class<Partitioner> partitionerClass;

        private Class<ProducerInterceptor> producerInterceptorClass;

        private List<String> interceptorClasses;

        private Builder() {
        }

        public Builder<K, V> bootstrapServers(String bootstrapServers) {
            this.bootstrapServers = bootstrapServers;
            return this;
        }

        public Builder<K, V> keySerializerClass(Class<Serializer<K>> keySerializerClass) {
            this.keySerializerClass = keySerializerClass;
            return this;
        }

        public Builder<K, V> valSerializerClass(Class<Serializer<V>> valSerializerClass) {
            this.valSerializerClass = valSerializerClass;
            return this;
        }

        public Builder<K, V> partitionerClass(Class<Partitioner> partitionerClass) {
            this.partitionerClass = partitionerClass;
            return this;
        }

        public Builder<K, V> interceptorClasses(List<String> interceptorClasses) {
            this.interceptorClasses = interceptorClasses;
            return this;
        }

        public KafkaConnectProducer<K, V> build() {
            Map<String, Object> configProps = new HashMap<>(Map.ofEntries(
                    Map.entry(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootstrapServers != null ? this.bootstrapServers : DEFAULT_BOOTSTRAP_SERVER),
                    Map.entry(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, this.keySerializerClass != null ? this.keySerializerClass : StringSerializer.class),
                    Map.entry(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, this.valSerializerClass != null ? this.valSerializerClass : StringSerializer.class)
            ));
            if (interceptorClasses != null) {
                configProps.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, interceptorClasses);
            }
            return new KafkaConnectProducer<K, V>(new KafkaProducer<K, V>(configProps));
        }
    }

    public static <K, V> KafkaConnectProducer<K, V> withDefaultConfig() {
        Map<String, Object> configProps = Map.ofEntries(
                Map.entry(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, DEFAULT_BOOTSTRAP_SERVER),
                Map.entry(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class),
                Map.entry(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class)
        );
        return new KafkaConnectProducer(new KafkaProducer<K, V>(configProps));
    }

    public static <K, V> KafkaConnectProducer<K, V> withProperties(Properties props) {
        return new KafkaConnectProducer<>(new KafkaProducer<K, V>(props));
    }

    public static <K, V> KafkaConnectProducer<K, V> fromProperties() throws IOException {
        Properties kafkaProps = new Properties();
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(KafkaConnectProducer.class.getClassLoader().getResourceAsStream("application.properties"))))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith(PRODUCER_CONFIG_PREFIX)) {
                    line = line.substring(PRODUCER_CONFIG_PREFIX.length());
                    String[] l = line.split("=");
                    String key = l[0];
                    String val = l[1];
                    if (key == null || val == null) {
                        throw new InstantiationError(String.format("property with no valid = separator, {}", line));
                    }
                    kafkaProps.put(key, val);
                }
            }
        }
        return new KafkaConnectProducer<K, V>(new KafkaProducer<K, V>(kafkaProps));
    }

    @Override
    public void publishFireNForget(String topic, V message, Map<String, byte[]> headers) {
        final ProducerRecord<K, V> record = new ProducerRecord<>(topic, message);
        headers.entrySet().forEach(e -> record.headers().add(e.getKey(), e.getValue()));
        this.producer.send(record);
    }

    @Override
    public void publishBlocking(String topic, V message, Map<String, byte[]> headers) {
        try {
            final ProducerRecord<K, V> record = new ProducerRecord<>(topic, message);
            headers.entrySet().forEach(e -> record.headers().add(e.getKey(), e.getValue()));
            Future<RecordMetadata> future = this.producer.send(record);
            future.get();
        } catch (Exception e) {
            log.error("Error while publishing. topic: {}, message: {}, error: {}", topic, message, e.getMessage());
        }
    }

    @Override
    public void publishNonBlocking(String topic, V message, Map<String, byte[]> headers, BiConsumer<RecordMetadata, Exception> callback) {
        try {
            final ProducerRecord<K, V> record = new ProducerRecord<>(topic, message);
            headers.entrySet().forEach(e -> record.headers().add(e.getKey(), e.getValue()));
            Future<RecordMetadata> future = this.producer.send(record, callback::accept);
            log.info("Message published: {}", future.toString());
        } catch (Exception e) {
            log.error("Error while sending: {}", e.getMessage());
            throw e;
        }
    }
}
