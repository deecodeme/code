package com.poc.code.design.BookMyShow.Communication;

import com.poc.code.design.BookMyShow.PubSub.Config;
import com.poc.code.design.BookMyShow.PubSub.Record;
import com.poc.code.design.BookMyShow.PubSub.Subscriber;

public class CancelledBookingSubscriber implements Subscriber {
    public CancelledBookingSubscriber(Config config) {

    }

    @Override
    public void notify(Record record) {

    }
}
