package com.poc.code.practices.design.BookMyShow.PubSub;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Config {
    Map<Event, List<Subscriber>> subs;

    public void subscribe(Event e, Subscriber s) {
        List<Subscriber> sub = subs.get(e);
        if (sub == null) {
            sub = new ArrayList<>();
            sub.add(s);
        } else {
            sub.add(s);
        }
        subs.put(e, sub);
    }

    public List<Subscriber> getSubscribers(Event e) {
        return subs.get(e);
    }
}
