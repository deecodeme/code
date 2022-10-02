package com.poc.code.practices.design.BookMyShow.Communication;

import com.poc.code.practices.design.BookMyShow.PubSub.Config;
import com.poc.code.practices.design.BookMyShow.PubSub.Record;
import com.poc.code.practices.design.BookMyShow.PubSub.Subscriber;

public class CancelledBookingSubscriber implements Subscriber {
    public CancelledBookingSubscriber(Config config) {

    }

    @Override
    public void notify(Record record) {

    }
}
