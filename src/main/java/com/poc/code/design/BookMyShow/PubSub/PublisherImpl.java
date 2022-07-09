package com.poc.code.design.BookMyShow.PubSub;

public class PublisherImpl implements Publisher {
    private Config config;

    public PublisherImpl(Config config) {
        this.config = config;
    }

    @Override
    public void publish(Event event, Record record) {
        for (Subscriber subs : config.getSubscribers(event)) {
            subs.notify(record);
        }
    }
}
