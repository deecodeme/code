package com.poc.code.design.ConcurrencyAndMultiThreading.LockAndCondition;

public class ConsumerImpl implements Consumer {
    private Channel channel;

    public ConsumerImpl(Channel channel) {
        this.channel = channel;
    }

    @Override
    public Object consume() {
        Object val = null;
        try {
            val = channel.get();
        } catch (InterruptedException e) {
            System.out.println("Exception in consumer: " + e.getMessage());
        }
        return val;
    }
}
