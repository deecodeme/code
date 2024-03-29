package com.poc.code.practices.design.StockExchange.messaging;

import java.util.Map;

public interface MessageBroker {
    void publish(Topic topic, Map<String, Object> message);

    void subscribe(Topic topic, Consumer consumer);
}
