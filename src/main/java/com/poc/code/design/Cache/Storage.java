package com.poc.code.design.Cache;

public interface Storage<V> {
    V create(String key, V val);

    V read(String key);

    V update(String key, V val);

    boolean delete(String key);

}
