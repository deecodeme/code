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
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
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

    public static <K, V> KafkaConnectProducer<K, V> withDefaultConfig() {
        Map<String, Object> configProps = Map.ofEntries(
                Map.entry(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, DEFAULT_BOOTSTRAP_SERVER),
                Map.entry(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class),
                Map.entry(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class)
        );
        return new KafkaConnectProducer(new KafkaProducer<K, V>(configProps));
    }

    public static <K, V> KafkaConnectProducer<K, V> withSerializers(Serializer<K> keySerializer, Serializer<V> valSerializer) {
        Map<String, Object> configProps = Map.of(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, DEFAULT_BOOTSTRAP_SERVER);
        return new KafkaConnectProducer<K, V>(new KafkaProducer<K, V>(configProps, keySerializer, valSerializer));
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
    public void publishFireNForget(String topic, V message) {
        this.producer.send(new ProducerRecord<>(topic, message));
    }

    @Override
    public void publishBlocking(String topic, V message) {
        try {
            Future<RecordMetadata> future = this.producer.send(new ProducerRecord<>(topic, message));
            future.get();
        } catch (Exception e) {
            log.error("Error while publishing. topic: {}, message: {}, error: {}", topic, message, e.getMessage());
        }
    }

    @Override
    public void publishNonBlocking(String topic, V message, BiConsumer<RecordMetadata, Exception> callback) {
        try {
            Future<RecordMetadata> future = this.producer.send(new ProducerRecord<>(topic, message), callback::accept);
            log.info("Message published: {}", future.toString());
        } catch (Exception e) {
            log.error("Error while sending: {}", e.getMessage());
            throw e;
        }
    }
}
