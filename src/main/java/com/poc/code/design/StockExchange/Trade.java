package com.poc.code.design.StockExchange;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class Trade {
    public static final String SPACE = " ";
    private String buyOrderId;
    private BigDecimal tradePrice;
    private int quantity;
    private String sellOrderId;
    private LocalDateTime executedAt;

    @Override
    public String toString() {
        return buyOrderId + SPACE + tradePrice.toString() + SPACE + quantity + SPACE + sellOrderId;
    }
}
