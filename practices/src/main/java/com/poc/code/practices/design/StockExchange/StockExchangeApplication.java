package com.poc.code.practices.design.StockExchange;

import com.poc.code.practices.design.StockExchange.messaging.Consumer;
import com.poc.code.practices.design.StockExchange.messaging.MessageBroker;
import com.poc.code.practices.design.StockExchange.messaging.MessageBrokerImpl;
import com.poc.code.practices.design.StockExchange.messaging.Topic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

public class StockExchangeApplication {

    public static final String ORDER_TYPE_BUY = "buy";

    public static void main(String[] args) {
        MessageBroker messageBroker = MessageBrokerImpl.INSTANCE;
        OrderService orderService = OrderServiceImpl.INSTANCE;
        messageBroker.subscribe(Topic.ORDER_PLACED, (Consumer) TradeServiceImpl.INSTANCE);
        try (BufferedReader reader = new BufferedReader(new FileReader("/Users/deepak.ku/Downloads/trades.txt"))) {
            reader.lines().forEach(line -> {
                String[] str = line.split(" ");
                Order order = parseOrder(str);
                orderService.placeOrder(order);
            });
        } catch (IOException | NullPointerException ex) {
            System.out.println("Invalid trades file");
        }
    }

    private static Order parseOrder(String[] str) {
        return Order.builder()
            .orderType(ORDER_TYPE_BUY.equals(str[3]) ? OrderType.BUY : OrderType.SELL)
            .orderId(str[0])
            .placedAt(str[1])
            .stock(str[2])
            .price(BigDecimal.valueOf(Double.parseDouble(str[4])))
            .quantity(Integer.parseInt(str[5]))
            .remaingQuantity(Integer.parseInt(str[5]))
            .build();
    }
}
