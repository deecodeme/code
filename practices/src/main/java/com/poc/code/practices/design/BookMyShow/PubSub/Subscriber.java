package com.poc.code.practices.design.BookMyShow.PubSub;

public interface Subscriber {
    void notify(Record record);
}
