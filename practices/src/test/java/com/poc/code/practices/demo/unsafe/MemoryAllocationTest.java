/* (C)2024 */
package com.poc.code.practices.demo.unsafe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MemoryAllocationTest {

    @Test
    void allocateMemory() {
        MemoryAllocation memoryAllocation = new MemoryAllocation();
        memoryAllocation.allocateMemory();
    }
}
