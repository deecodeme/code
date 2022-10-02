package com.poc.code.practices.design.Cache;

public interface EvictableStorage<T> {
    T evict();
}
