package com.poc.code.design.ConcurrencyAndMultiThreading.LockAndCondition;

public interface Channel {
    void put(Object val) throws InterruptedException;

    Object get() throws InterruptedException;
}
