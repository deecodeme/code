package com.poc.code.design.StockExchange;

import com.poc.code.design.StockExchange.messaging.MessageBroker;
import com.poc.code.design.StockExchange.messaging.MessageBrokerImpl;
import com.poc.code.design.StockExchange.messaging.Topic;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class OrderServiceImpl implements OrderService {
    public static final OrderServiceImpl INSTANCE = new OrderServiceImpl();
    private List<Order> orders;
    private MessageBroker messageBroker;

    private OrderServiceImpl() {
        this.messageBroker = MessageBrokerImpl.INSTANCE;
        this.orders = new ArrayList<>();
    }

    @Override
    public void placeOrder(Order order) {
        this.orders.add(order);
        this.messageBroker.publish(Topic.ORDER_PLACED, Map.of("order", order));
    }
}
