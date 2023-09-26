package com.poc.code.practices.design.ConcurrencyAndMultiThreading.LockAndCondition;

public class ProducerImpl implements Producer {
    private Channel channel;

    public ProducerImpl(Channel channel) {
        this.channel = channel;
    }

    @Override
    public void produce(Object val) {
        try {
            Thread.sleep(1000);
            channel.put(val);
            System.out.println("Success producing message");
        } catch (InterruptedException e) {
            System.out.println("Exception in producer: " + e.getMessage());
        }
    }
}
