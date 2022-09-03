package com.poc.kafka.spring.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.Map;

@Configuration
public class KafkaTopicsConfiguration {

    @Value("${kafka.bootstrapServer}")
    private String bootstrapServer;

    @Value("${kafka.topic.foo}")
    private String fooTopicName;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configProps = Map.of(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        return new KafkaAdmin(configProps);
    }

    @Bean
    public NewTopic fooTopic() {
        return new NewTopic(fooTopicName, 1, (short) 1);
    }
}
