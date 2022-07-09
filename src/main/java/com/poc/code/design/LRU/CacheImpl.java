package com.poc.code.design.LRU;

public class CacheImpl<K, V> implements Cache<K, V> {
    private CacheSerialiser keySerialiser;
    private CacheSerialiser valueSerialiser;

    private CacheImpl(CacheSerialiser keySerialiser, CacheSerialiser valueSerialiser) {
        this.keySerialiser = keySerialiser;
        this.valueSerialiser = valueSerialiser;
    }

    @Override
    public boolean set(K k, V v) {
        return false;
    }

    @Override
    public V get(K k) {
        return null;
    }
}
