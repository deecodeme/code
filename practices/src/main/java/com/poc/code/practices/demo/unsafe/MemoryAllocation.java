package com.poc.code.practices.demo.unsafe;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

public class MemoryAllocation {
    private static final Unsafe UNSAFE;
    private static final long HEAP_BASE_OFFSET;

    static {
        try {
            Field hu = ClassLoader.getSystemClassLoader().getClass().getSuperclass().getDeclaredField("unsafe");
            hu.setAccessible(true);
            UNSAFE = (Unsafe) hu.get(null);
            HEAP_BASE_OFFSET = UNSAFE.arrayBaseOffset(ClassLoader.getSystemClassLoader().getClass());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void allocateMemory() {
        try {
            long objectAddress = (long) UNSAFE.allocateInstance(ClassLoader.getSystemClassLoader().getClass());
            System.out.println("Allocated object address: " + Long.toHexString(objectAddress));
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
}
