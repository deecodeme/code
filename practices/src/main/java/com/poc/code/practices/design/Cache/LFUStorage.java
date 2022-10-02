package com.poc.code.practices.design.Cache;

public class LFUStorage<T> implements Storage<T>, EvictableStorage<T>{
    @Override
    public T evict() {
        return null;
    }

    @Override
    public T create(String key, T val) {
        return null;
    }

    @Override
    public T read(String key) {
        return null;
    }

    @Override
    public T update(String key, T val) {
        return null;
    }

    @Override
    public boolean delete(String key) {
        return false;
    }
}
