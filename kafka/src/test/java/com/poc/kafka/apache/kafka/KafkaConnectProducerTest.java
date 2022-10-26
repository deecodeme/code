package com.poc.kafka.apache.kafka;

import com.poc.kafka.MessagePublisher;
import com.poc.kafka.apache.kafka.serializers.Customer;
import com.poc.kafka.apache.kafka.serializers.CustomerSerializer;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.function.BiConsumer;

class KafkaConnectProducerTest {
    private static final String BOOTSTRAP_SERVERS_LOCAL = "localhost:9092";
    private static final String KAFKA_AVRO_SERIALIZER = "io.confluent.kafka.serializers.KafkaAvroSerializer";
    private static final String SCHEMA_REGISTRY_LOCAL = "http://localhost:8081";
    public static final String MESSAGE_ORIGIN = "ORIGIN";
    public static final String ORDER_SERVICE = "ORDER_SERVICE";
    Logger log = LoggerFactory.getLogger(KafkaConnectProducerTest.class);

    private final BiConsumer<RecordMetadata, Exception> producerCallbackConsumer = (metadata, e) -> {
        if (e != null) {
            log.error("Error while publishing. error: {}", e.getMessage());
        } else {
            log.info("successfully published, metadata: {}", metadata.toString());
        }
    };

    @Test
    void publish() throws IOException {
        MessagePublisher<String, String> kafkaConnectProducer = KafkaConnectProducer.fromProperties();
        kafkaConnectProducer.publishBlocking("test", "test message", Map.of(MESSAGE_ORIGIN, ORDER_SERVICE.getBytes(StandardCharsets.UTF_8)));
    }

    @Test
    void publishWithDefaultConfig() throws IOException {
        MessagePublisher<String, String> kafkaConnectProducer = KafkaConnectProducer.withDefaultConfig();
        kafkaConnectProducer.publishBlocking("test", "test message", Map.of(MESSAGE_ORIGIN, ORDER_SERVICE.getBytes(StandardCharsets.UTF_8)));
    }

    @Test
    void publishCustomSerializer() throws InterruptedException {
        MessagePublisher<String, Customer> kafkaConnectProducer = KafkaConnectProducer.Builder
                .INSTANCE
                .bootstrapServers(BOOTSTRAP_SERVERS_LOCAL)
                .keySerializerClass(StringSerializer.class)
                .valSerializerClass(CustomerSerializer.class)
                .interceptorClasses(List.of("com.poc.kafka.apache.kafka.interceptors.ProducerRecordsCounterInterceptor",
                        "com.poc.kafka.apache.kafka.interceptors.ProducerLoggingInterceptor"))
                .build();
        kafkaConnectProducer.publishBlocking("test",
                new Customer(1234, "deepak"),
                Map.of(MESSAGE_ORIGIN, ORDER_SERVICE.getBytes(StandardCharsets.UTF_8)));
        // sleep to observe counter interceptor logging records count
        Thread.sleep(20000);
    }

    @Test
    void publishWithAvroSerializer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", BOOTSTRAP_SERVERS_LOCAL);
        props.put("key.serializer", KAFKA_AVRO_SERIALIZER);
        props.put("value.serializer", KAFKA_AVRO_SERIALIZER);
        props.put("schema.registry.url", SCHEMA_REGISTRY_LOCAL);
        KafkaConnectProducer<String, customer.avro.Customer> kafkaConnectProducer = KafkaConnectProducer.withProperties(props);
        kafkaConnectProducer.publishBlocking("test",
                customer.avro.Customer.newBuilder().setId(123).setName("Deepak").setFavouriteColour("Green").build(),
                Map.of(MESSAGE_ORIGIN, ORDER_SERVICE.getBytes(StandardCharsets.UTF_8)));
    }

    @Test
    void publishAvoGenericRecord() {
        Properties props = new Properties();
        props.put("bootstrap.servers", BOOTSTRAP_SERVERS_LOCAL);
        props.put("key.serializer", KAFKA_AVRO_SERIALIZER);
        props.put("value.serializer", KAFKA_AVRO_SERIALIZER);
        props.put("schema.registry.url", SCHEMA_REGISTRY_LOCAL);

        String schemaString = "{\n" +
                "  \"namespace\": \"customer.avro\",\n" +
                "  \"type\": \"record\",\n" +
                "  \"name\": \"Customer\",\n" +
                "  \"fields\": [\n" +
                "    {\n" +
                "      \"name\": \"id\",\n" +
                "      \"type\": \"int\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"name\",\n" +
                "      \"type\": \"string\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"favourite_colour\",\n" +
                "      \"type\": [\n" +
                "        \"string\",\n" +
                "        \"null\"\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        KafkaConnectProducer<String, GenericRecord> kafkaConnectProducer = KafkaConnectProducer.withProperties(props);
        Schema.Parser parser = new Schema.Parser();
        Schema schema = parser.parse(schemaString);

        GenericRecord genericRecord = new GenericData.Record(schema);
        genericRecord.put("id", 1234);
        genericRecord.put("name", "Deepak");
        genericRecord.put("favourite_colour", "Green");
        kafkaConnectProducer.publishBlocking("test", genericRecord, Map.of(MESSAGE_ORIGIN, ORDER_SERVICE.getBytes(StandardCharsets.UTF_8)));
    }

    @Test
    void publishWithCallback() throws IOException {
        MessagePublisher<String, String> kafkaConnectProducer = KafkaConnectProducer.fromProperties();
        kafkaConnectProducer.publishNonBlocking("test", "callback", Map.of(MESSAGE_ORIGIN, ORDER_SERVICE.getBytes(StandardCharsets.UTF_8)), producerCallbackConsumer);
    }
}