package com.poc.code.practices.effectiveJava.commonMethods;

import java.util.Objects;

/*
1. Always override hashcode() when you override equals() to make sure Collection classes function properly on your classes.
2. When the hashCode method is invoked on an object repeatedly during an execution of an application,it must consistently return the same value,
provided no information used in equals comparisons is modified. This value need not remain consistent from one execution
of an application to another.
3. If two objects are equal according to the equals(Object) method, then calling hashCode on the two objects must produce the same integer result.
4. If two objects are unequal according to the equals(Object) method, it is not required that calling hashCode on each of the objects must produce distinct results. However, the programmer should
be aware that producing distinct results for unequal objects may improve the performance of hash tables.
5. You can cache the hashcode, if Class is immutable. Cache should either be created during initialisation or as a lazy init, when hashcode() is called first time.
6. change hashCode always with a change in equals()

Approach
==========
1. Declare an int variable named result, and initialize it to the hash code c for the first significant field in your object, as computed in step 2.a. (Recall
         from Item 10 that a significant field is a field that affects equals comparisons.)
2. For every remaining significant field f in your object, do the following:
    a. Compute an int hash code c for the field:
        i. If the field is of a primitive type, compute Type.hashCode(f), where Type is the boxed primitive class corresponding to f’s type.
        ii. If the field is an object reference and this class’s equals method compares the field by recursively invoking equals,
         recursively invoke hashCode on the field. If a more complex comparison is required, compute a “canonical representation”
                 for this field and invoke hashCode on the canonical representation. If the value of the field is null, use 0 (or some other constant, but 0 is traditional).
        iii. If the field is an array, treat it as if each significant element were a separate
                 field. That is, compute a hash code for each significant element by applying these
                 rules recursively, and combine the values per step 2.b. If the array[…]”
    b. Combine the hash code c computed in step 2.a into result as follows:
        result = 31 * result + c;
3. Return result.
4. unit test to verify if equal objects has the same has code.
 */
public class HashcodePractices {
    public static class PhoneNumberWithoutHashcode {
        private final short areaCode, prefix, lineNum;

        public PhoneNumberWithoutHashcode(int areaCode, int prefix, int lineNum) {
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
            if (!(obj instanceof PhoneNumberWithoutHashcode)) return false;
            PhoneNumberWithoutHashcode ph = (PhoneNumberWithoutHashcode) obj;
            return ph.lineNum == this.lineNum && ph.prefix == this.prefix && ph.areaCode == this.areaCode;
        }
    }

    public static class PhoneNumberWithHashcode {
        private int hashcode = 0; // cache the hashcode only if object is Immutable
        private final short areaCode, prefix, lineNum;

        public PhoneNumberWithHashcode(int areaCode, int prefix, int lineNum) {
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
            if (!(obj instanceof PhoneNumberWithHashcode)) return false;
            PhoneNumberWithHashcode ph = (PhoneNumberWithHashcode) obj;
            return ph.lineNum == this.lineNum && ph.prefix == this.prefix && ph.areaCode == this.areaCode;
        }

        @Override
        public int hashCode() {
            if (this.hashcode != 0) return this.hashcode;
            this.hashcode = Short.hashCode(this.lineNum);
            this.hashcode = 31 * this.hashcode + Short.hashCode(this.areaCode);
            this.hashcode = 31 * this.hashcode + Short.hashCode(this.prefix);
            return this.hashcode;
        }

        public int hashCodeFromObjects() { //mediocre performance
            return Objects.hash(this.lineNum, this.areaCode, this.prefix);
        }
    }
}
