package com.poc.code.design.StockExchange;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
@Builder
public class Order {
    @NonNull
    private OrderType orderType;
    @NonNull
    private String orderId;
    @NonNull
    private BigDecimal price;
    @NonNull
    private int quantity;
    private int remaingQuantity;
    @NonNull
    private String placedAt;
    private String stock;

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", remaingQuantity=" + remaingQuantity +
                ", placedAt='" + placedAt + '\'' +
                ", stock='" + stock + '\'' +
                '}';
    }
}
