package com.poc.code.practices.effectiveJava.memoryManagement;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.WeakHashMap;

public class EliminateObsoleteReferences {
    public static class Stack {
        private Object[] stack;
        private int size = 0;

        WeakHashMap weakHashMap;
        private static final int INITIAL_SIZE = 10;

        public Stack() {
            this.stack = new Object[INITIAL_SIZE];
        }

        public void push(Object e) {
            ensureCapacity();
            this.stack[this.size++] = e;
        }

        // The object references for popped element are not being de-referenced and hence
        // shall not be garbage collected
        public Object pop() {
            handleEmptiStack();
            return this.stack[--size];
        }


        public Object popWithObsoleteRefElimination() {
            handleEmptiStack();
            Object e = this.stack[--this.size];
            this.stack[size] = null;
            return e;
        }

        private void handleEmptiStack() {
            if (this.size == 0) {
                throw new EmptyStackException();
            }
        }

        private void ensureCapacity() {
            if (size == this.stack.length) {
                this.stack = Arrays.copyOf(this.stack, this.size * 2);
            }
        }
    }
}
