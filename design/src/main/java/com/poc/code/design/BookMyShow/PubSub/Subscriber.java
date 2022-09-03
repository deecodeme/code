package com.poc.code.design.BookMyShow.PubSub;

public interface Subscriber {
    void notify(Record record);
}
