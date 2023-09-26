package com.poc.code.practices.effectiveJava.commonMethods;

import java.util.Arrays;
import java.util.EmptyStackException;

/*
Immutable classes should not provide
 */
public class ClonePractices {
    public static class PhoneNumber {
        private final short areaCode, prefix, lineNum;

        public PhoneNumber(int areaCode, int prefix, int lineNum) {
            this.areaCode = validateRange(areaCode, 999, "Area Code");
            this.prefix = validateRange(prefix, 999, "Prefix");
            this.lineNum = validateRange(lineNum, 9999, "Line Num");
        }

        private short validateRange(int val, int max, String comment) {
            if (val < 0 || val > max) {
                throw new IllegalArgumentException(String.format("Invalid %s. val: %s, min %s, max %s", comment, val, 0, max));
            }
            return (short) val;
        }

        /**
         * With equals() override we should always override hashcode() for deterministic working of Collection classes
         *
         * @param obj
         * @return
         */
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof PhoneNumber ph)) return false;
            return ph.lineNum == this.lineNum && ph.prefix == this.prefix && ph.areaCode == this.areaCode;
        }

        /**
         * Returns the string representation of this phone number in format of XXX:YYY:ZZZZ
         * where, XXX is area code, YYY is prefix, ZZZZ is line number. each of the capital letter represents a single
         * decimal digit. If any of the three numbers is smaller in length, it will be padded with leading zeros.
         *
         * @return
         */
        @Override
        public String toString() {
            return String.format("%03d:%03d:%04d", this.areaCode, this.prefix, this.lineNum);
        }

        @Override
        public Object clone() {
            return new PhoneNumber(this.areaCode, this.prefix, this.lineNum);
        }
    }

    public static class CloneablePhoneNumber implements Cloneable {
        private final short areaCode, prefix, lineNum;

        public CloneablePhoneNumber(int areaCode, int prefix, int lineNum) {
            this.areaCode = validateRange(areaCode, 999, "Area Code");
            this.prefix = validateRange(prefix, 999, "Prefix");
            this.lineNum = validateRange(lineNum, 9999, "Line Num");
        }

        private short validateRange(int val, int max, String comment) {
            if (val < 0 || val > max) {
                throw new IllegalArgumentException(String.format("Invalid %s. val: %s, min %s, max %s", comment, val, 0, max));
            }
            return (short) val;
        }

        /**
         * With equals() override we should always override hashcode() for deterministic working of Collection classes
         *
         * @param obj
         * @return
         */
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof CloneablePhoneNumber ph)) return false;
            return ph.lineNum == this.lineNum && ph.prefix == this.prefix && ph.areaCode == this.areaCode;
        }

        /**
         * Returns the string representation of this phone number in format of XXX:YYY:ZZZZ
         * where, XXX is area code, YYY is prefix, ZZZZ is line number. each of the capital letter represents a single
         * decimal digit. If any of the three numbers is smaller in length, it will be padded with leading zeros.
         *
         * @return
         */
        @Override
        public String toString() {
            return String.format("%03d:%03d:%04d", this.areaCode, this.prefix, this.lineNum);
        }

        /**
         * “Though Object’s clone method returns Object, this clone method returns PhoneNumber.
         * It is legal and desirable to do this because Java supports covariant return types.
         * In other words, an overriding method’s return type can be a subclass of the overridden
         * method’s return type. This eliminates the need for casting in the client. We must
         * cast the result of super.clone from Object to PhoneNumber before returning it, but the cast is guaranteed to succeed.”
         */
        @Override
        public CloneablePhoneNumber clone() {
            try {
                return (CloneablePhoneNumber) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }
    }

    /**
     * Another Usage with internal state
     */
    public static class Stack implements Cloneable {
        private static final int DEFAULT_INITIAL_CAPACITY = 16;
        private Object[] elements;
        private int size = 0;

        public Stack() {
            elements = new Object[DEFAULT_INITIAL_CAPACITY];
        }

        public void push(Object e) {
            ensureCapacity();
            this.elements[this.size++] = e;
        }

        public Object pop() {
            if (this.size == 0) throw new EmptyStackException();
            Object e = this.elements[--this.size];
            this.elements[this.size] = null; // nullify the reference for garbage collection
            return e;
        }

        private void ensureCapacity() {
            if (this.size == this.elements.length) {
                this.elements = Arrays.copyOf(this.elements, this.size * 2);
            }
        }

        /**
         * “Suppose you want to make this class cloneable. If the clone method merely returns super.clone(),
         * the resulting Stack instance will have the correct value in its size field,
         * but its elements field will refer to the same array as the original Stack instance. Modifying the original will destroy the invariants in the clone and vice
         * versa. You will quickly find that your program produces nonsensical results or throws a NullPointerException.
         * <p>
         * This situation could never occur as a result of calling the sole constructor in the Stack class.
         * In effect, the clone method functions as a constructor; you must ensure that it does no harm to the original
         * object and that it properly establishes invariants on the clone. In order for the clone method on Stack to work properly,
         * it must copy the internals of the stack. The easiest way to do
         * this is to call clone recursively on the elements array:”
         */
        @Override
        public Stack clone() {
            try {
                Stack copy = (Stack) super.clone();
                copy.elements = this.elements.clone();
                return copy;
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
