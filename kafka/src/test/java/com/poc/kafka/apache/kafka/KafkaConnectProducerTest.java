package com.poc.kafka.apache.kafka;

import com.poc.kafka.MessagePublisher;
import com.poc.kafka.apache.kafka.serializers.Customer;
import com.poc.kafka.apache.kafka.serializers.CustomerSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;
import java.util.function.BiConsumer;

class KafkaConnectProducerTest {
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
        kafkaConnectProducer.publishBlocking("test", "test message");
    }

    @Test
    void publishCustomSerializer() {
        MessagePublisher<String, Customer> kafkaConnectProducer = KafkaConnectProducer.withSerializers(new StringSerializer(), new CustomerSerializer());
        kafkaConnectProducer.publishBlocking("test", new Customer(1234, "deepak"));
    }

    @Test
    void publishWithAvroSerializer() {
        KafkaConnectProducer kafkaConnectProducer = KafkaConnectProducer.withSerializers(new KafkaAvroSerializer(), new KafkaAvroSerializer());
        kafkaConnectProducer.publishBlocking("test", customer.avro.Customer.newBuilder().setId(123).setName("Deepak").setFavouriteColour("Green").build());
    }

    @Test
    void publishAvoGenericRecord() {
        Properties props = new Properties();
        props.put("bootstrap.server", "localhost:9092");
        props.put("key.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
        props.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");

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
        kafkaConnectProducer.publishBlocking("test", genericRecord);
    }

    @Test
    void publishWithCallback() throws IOException {
        MessagePublisher<String, String> kafkaConnectProducer = KafkaConnectProducer.fromProperties();
        kafkaConnectProducer.publishNonBlocking("test", "callback", producerCallbackConsumer);
    }
}