package com.poc.code.practices.design.StockExchange;

import com.poc.code.practices.design.StockExchange.messaging.Consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.logging.Logger;

public class TradeServiceImpl implements TradeService, Consumer {
    private static final Logger logger = Logger.getLogger(TradeServiceImpl.class.getName());

    public static TradeService INSTANCE = new TradeServiceImpl();
    /*
    1. A trade is executed when buy price is higher than sell price
    2. Lower selling prices and higher buying prices takes priority
    3. if there are orders at same price, FCFS should be followed
     */
    private PriorityBlockingQueue<Order> sellOrdersQueue;
    private PriorityBlockingQueue<Order> buyOrdersQueue;

    private TradeServiceImpl() {
        this.sellOrdersQueue = new PriorityBlockingQueue<>(10, (o1, o2) -> {
            if (o1.getPrice().equals(o2.getPrice())) {
                return o1.getPlacedAt().compareTo(o2.getPlacedAt());
            } else {
                return o1.getPrice().compareTo(o2.getPrice());
            }
        });
        this.buyOrdersQueue = new PriorityBlockingQueue<>(10, (o1, o2) -> {
            if (o1.getPrice().equals(o2.getPrice())) {
                return o1.getPlacedAt().compareTo(o2.getPlacedAt());
            } else {
                return o2.getPrice().compareTo(o1.getPrice());
            }
        });
    }

    @Override
    public void executeTrade() {
        while (!this.buyOrdersQueue.isEmpty()) {
            Order buyOrder = this.buyOrdersQueue.peek();
            List<Trade> trades = executeTrade(buyOrder);
            trades.stream().forEach(t -> logger.info(t.toString()));
            if (buyOrder.getRemaingQuantity() == 0) {
                this.buyOrdersQueue.poll();
            } else {
                break;
            }
        }
    }

    private List<Trade> executeTrade(Order buyOrder) {
        List<Trade> trades = new ArrayList<>();
        while (!this.sellOrdersQueue.isEmpty() && buyOrder.getRemaingQuantity() > 0) {
            if (this.sellOrdersQueue.peek().getPrice().compareTo(buyOrder.getPrice()) > 0) {
                break;
            }
            Order sellOrder = this.sellOrdersQueue.poll();
            if (sellOrder.getQuantity() > buyOrder.getRemaingQuantity()) { // full quantity can be sold out
                trades.add(Trade
                    .builder()
                    .buyOrderId(buyOrder.getOrderId())
                    .tradePrice(sellOrder.getPrice())
                    .quantity(buyOrder.getRemaingQuantity())
                    .sellOrderId(sellOrder.getOrderId()).build());
                sellOrder.setQuantity(sellOrder.getQuantity() - buyOrder.getRemaingQuantity());
                this.sellOrdersQueue.offer(sellOrder);
                buyOrder.setRemaingQuantity(0);
                break;
            } else { // sell order quantity less than buy order quantity
                trades.add(Trade
                    .builder()
                    .buyOrderId(buyOrder.getOrderId())
                    .tradePrice(sellOrder.getPrice())
                    .quantity(sellOrder.getQuantity())
                    .sellOrderId(sellOrder.getOrderId()).build());
                buyOrder.setRemaingQuantity(buyOrder.getRemaingQuantity() - sellOrder.getQuantity());
            }
        }
        return trades;
    }

    @Override
    public void process(Map<String, Object> message) {
        Order order = (Order) message.get("order");
        Objects.requireNonNull(order);
        if (order.getOrderType() == OrderType.SELL) {
            this.sellOrdersQueue.offer(order);
        } else {
            this.buyOrdersQueue.offer(order);
        }
        this.executeTrade();
    }
}
