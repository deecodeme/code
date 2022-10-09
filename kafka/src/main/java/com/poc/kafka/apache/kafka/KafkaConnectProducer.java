package com.poc.kafka.apache.kafka;

import com.poc.kafka.MessagePublisher;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Future;
import java.util.function.BiConsumer;

public class KafkaConnectProducer implements MessagePublisher {
    private static final String PRODUCER_CONFIG_PREFIX = "kafka.producer.";
    private final Logger log = LoggerFactory.getLogger(KafkaConnectProducer.class);

    private final Producer<String, String> producer;

    private KafkaConnectProducer(Producer<String, String> producer) {
        this.producer = producer;
    }

    public static KafkaConnectProducer withDefaultConfig() {
        Map<String, Object> configProps = Map.ofEntries(
                Map.entry(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"),
                Map.entry(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class),
                Map.entry(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class)
        );
        return new KafkaConnectProducer(new KafkaProducer<String, String>(configProps));
    }

    public static KafkaConnectProducer fromProperties() throws IOException {
        Properties kafkaProps = new Properties();
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(KafkaConnectProducer.class.getClassLoader().getResourceAsStream("application.properties")))) {
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
        return new KafkaConnectProducer(new KafkaProducer(kafkaProps));
    }

    @Override
    public void publishFireNForget(String topic, String message) {
        this.producer.send(new ProducerRecord<>(topic, message));
    }

    @Override
    public void publishBlocking(String topic, String message) {
        try {
            Future<RecordMetadata> future = this.producer.send(new ProducerRecord<>(topic, message));
            future.get();
        } catch (Exception e) {
            log.error("Error while publishing. topic: {}, message: {}, error: {}", topic, message, e.getMessage());
        }
    }

    @Override
    public void publishNonBlocking(String topic, String message, BiConsumer<RecordMetadata, Exception> callback) {
        this.producer.send(new ProducerRecord<>(topic, message), callback::accept);
    }
}
