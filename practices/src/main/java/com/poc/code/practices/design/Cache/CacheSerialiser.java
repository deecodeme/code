package com.poc.code.practices.design.Cache;

import java.io.IOException;

public interface CacheSerialiser<T> {
    byte[] serialise(T val) throws IOException;

    T deserialise(byte[] val) throws IOException, ClassNotFoundException;

}
