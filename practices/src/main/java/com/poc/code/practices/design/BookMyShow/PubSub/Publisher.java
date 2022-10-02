package com.poc.code.practices.design.BookMyShow.PubSub;

public interface Publisher {
    void publish(Event event, Record record);
}
