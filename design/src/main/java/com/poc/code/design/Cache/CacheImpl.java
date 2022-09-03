package com.poc.code.design.Cache;

public class CacheImpl<V> implements Cache<V> {
    private int capacity;
    private int size;
    private Storage<V> storage;
    private CacheSerialiser<V> valueSerialiser;

    private CacheImpl(CacheSerialiser<V> valueSerialiser,
                      Storage<V> storage) {
        this.valueSerialiser = valueSerialiser;
        this.storage = storage;

    }

    @Override
    public V set(String k, V v) {
        return this.storage.create(k, v);
    }

    @Override
    public V get(String k) {
        return this.storage.read(k);
    }
}
