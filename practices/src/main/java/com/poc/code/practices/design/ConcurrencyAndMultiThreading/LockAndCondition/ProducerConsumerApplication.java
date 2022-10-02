package com.poc.code.practices.design.ConcurrencyAndMultiThreading.LockAndCondition;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProducerConsumerApplication {
    private static final int POOL_SIZE = 10;

    public static void main(String[] args) {
        Channel channel = new ChannelImpl();
        ExecutorService executorService = Executors.newFixedThreadPool(POOL_SIZE);
        Producer producer = new ProducerImpl(channel);
        Consumer consumer = new ConsumerImpl(channel);
        Random random = new Random();

        boolean isProduce = true;
        for (int i = 1; i <= 20; i++) {
            if (isProduce) {
                executorService.execute(() -> producer.produce(random.nextInt()));
                isProduce = false;
            } else {
                executorService.submit(() -> consumer.consume());
                isProduce = true;

            }
        }
    }

}
