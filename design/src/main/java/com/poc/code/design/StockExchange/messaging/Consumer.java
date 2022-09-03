package com.poc.code.design.StockExchange.messaging;

import java.util.Map;

public interface Consumer {
    public void process(Map<String, Object> message);
}
