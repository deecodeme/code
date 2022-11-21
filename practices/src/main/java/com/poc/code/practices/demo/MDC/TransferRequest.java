package com.poc.code.practices.demo.MDC;

import java.util.UUID;

public class TransferRequest {
    private String from;
    private String to;
    private int amount;
    private String requestId;

    public static TransferRequest of(String from, String to, int amount) {
        return new TransferRequest(from, to, amount);
    }

    private TransferRequest(String from, String to, int amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.requestId = UUID.randomUUID().toString();
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public int getAmount() {
        return amount;
    }

    public String getRequestId() {
        return requestId;
    }

    @Override
    public String toString() {
        return "TransferRequest{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", amount=" + amount +
                ", requestId='" + requestId + '\'' +
                '}';
    }
}
