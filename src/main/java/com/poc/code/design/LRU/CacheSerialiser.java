package com.poc.code.design.LRU;

public interface CacheSerialiser<T> {
    byte[] serialise(T val);

    T deserialise(byte[] val);

}
