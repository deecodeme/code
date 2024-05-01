/* (C)2024 */
package com.poc.code.practices.demo.varHandle;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public class ArrayHandle {
    public static int setArrayElement(int val) {
        int[] array = new int[10];

        // Get a varHandle for array element
        VarHandle arrayHandle = MethodHandles.arrayElementVarHandle(int[].class);

        // Use the varHandle to access an element
        // Coordinated types are the array and the index
        arrayHandle.set(array, 5, val);

        return array[5];
    }

    public static int compareAndSet(int index, int compareTo, int setTo) {
        int[] array = new int[10];
        int currentVal = -1;
        VarHandle arrayHandle = MethodHandles.arrayElementVarHandle(int[].class);
        while (!arrayHandle.compareAndSet(array, index, compareTo, setTo)) {
            currentVal = (int) arrayHandle.getVolatile(array, index);
            arrayHandle.set(array, index, currentVal + 1);
        }
        return (int) arrayHandle.getVolatile(array, index);
    }
}
