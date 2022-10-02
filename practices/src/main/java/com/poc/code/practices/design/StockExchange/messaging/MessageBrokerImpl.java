package com.poc.code.practices.design.StockExchange.messaging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageBrokerImpl implements MessageBroker {
    private Map<Topic, List<Consumer>> topicMapping;
    public static final MessageBrokerImpl INSTANCE = new MessageBrokerImpl();

    private MessageBrokerImpl() {
        this.topicMapping = new HashMap<>();
    }

    @Override
    public void publish(final Topic topic, final Map<String, Object> message) {
        this.topicMapping.get(topic).stream().forEach(consumer -> consumer.process(message));
    }

    @Override
    public void subscribe(final Topic topic, final Consumer consumer) {
        List<Consumer> consumers = this.topicMapping.getOrDefault(topic, new ArrayList<>());
        consumers.add(consumer);
        this.topicMapping.put(topic, consumers);
    }
}
