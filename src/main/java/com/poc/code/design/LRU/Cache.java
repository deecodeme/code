package com.poc.code.design.LRU;

public interface Cache<K, V> {
    boolean set(K k, V v);
    V get (K k);
}
