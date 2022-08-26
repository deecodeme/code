package com.poc.code.design.Cache;

public interface EvictableStorage<T> {
    T evict();
}
