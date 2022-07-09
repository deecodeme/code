package com.poc.code.design.BookMyShow.PubSub;

public interface Publisher {
    void publish(Event event, Record record);
}
