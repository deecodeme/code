package com.poc.code.practices.design.Cache;

import com.poc.code.ds.dll.DLL;
import com.poc.code.ds.dll.Node;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class LRUStorage<V> implements Storage<V>, EvictableStorage<V> {
    private int capacity;
    private int size;
    private Map<String, CacheElement<V>> cacheMap;
    private DLL<String> dll; // dll is to track LRU

    public LRUStorage(int capacity) {
        this.cacheMap = new HashMap<>();
        this.dll = new DLL<>();
        this.size = 0;
        this.capacity = capacity;
    }

    @Override
    public V create(String key, V val) {
        if (this.size == this.capacity) {
            this.evict();
        }
        Node<String> node = this.dll.insertFirst(key);
        cacheMap.put(key, new CacheElement<>(val, node));
        this.size++;
        return val;
    }

    /*
    Time Complexity: O(1) from refMap
    Time Complexity:
     */
    @Override
    public V read(String key) {
        CacheElement<V> n = this.cacheMap.get(key);
        Objects.requireNonNull(n);
        this.dll.moveToFront(n.getDllRef());
        return n.getData();
    }

    @Override
    public V update(String key, V val) {
        CacheElement<V> cacheElement = cacheMap.get(key);
        Objects.requireNonNull(cacheElement);
        cacheElement.setData(val);
        this.dll.moveToFront(cacheElement.getDllRef());
        return val;
    }

    @Override
    public boolean delete(String key) {
        try {
            CacheElement<V> cacheElement = this.cacheMap.get(key);
            Objects.requireNonNull(cacheElement);
            this.dll.delete(cacheElement.getDllRef());
            this.cacheMap.remove(key);
            this.size--;
            return true;
        } catch (NullPointerException ex) {
            log.error("Error while delete: {} ", key);
        }
        return false;
    }

    @Override
    public V evict() {
        V d;
        Node<String> node = this.dll.getLast().orElseThrow();
        d = this.cacheMap.get(node.getData()).getData();
        this.dll.deleteLast();
        this.cacheMap.remove(node.getData());
        this.size--;
        return d;
    }

    @Data
    @AllArgsConstructor
    private static class CacheElement<V> {
        private V data;
        private Node<String> dllRef;
    }
}
