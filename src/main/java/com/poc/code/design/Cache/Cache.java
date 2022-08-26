package com.poc.code.design.Cache;

public interface Cache<V> {
    V set(String k, V v);

    V get(String k);
}
