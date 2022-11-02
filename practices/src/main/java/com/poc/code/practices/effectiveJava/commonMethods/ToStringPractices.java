package com.poc.code.practices.effectiveJava.commonMethods;

import java.util.Objects;

/*
1. ToString is not as strict in contract as equals() and hashcode(). It is to provide a concise and meaningful string representation.
2. toString() is automatically invoked by printf, println, string concatenation operator, assert, printed by debugger.
3. A meaningful String representation of an Object help debug.
4. When practical, the toString method should return all of the interesting information contained in the object
5. If there are many non-significant information or information size is large, then a summary representation should be returned.
6. One important decision you’ll have to make when implementing a toString method is whether to specify the format of the return value in the documentation.
    It is recommended that you do this for value classes, such as phone number or matrix. The advantage of specifying the format is that it
    serves as a standard, unambiguous, human-readable representation of the object. This
    representation can be used for input and output and in persistent human-readable data
    objects, such as CSV files. If you specify the format, it’s usually a good idea to
    provide a matching static factory or constructor so programmers can easily translate
    back and forth between the object and its string representation. This approach is
    taken by many value classes in the Java platform libraries, including BigInteger, BigDecimal, and most of the boxed primitive classes.
 7. The disadvantage of specifying the format of the toString return value is that once you’ve specified it, you’re stuck with it for life, assuming
    your class is widely used. Programmers will write code to parse the representation,
    to generate it, and to embed it into persistent data. If you change the representation
    in a future release, you’ll break their code and data, and they will yowl. By choosing
    not to specify a format, you preserve the flexibility to add information or improve
    the format in a subsequent release.
8. Whether or not you decide to specify the format, you should clearly document your intentions. If you specify the format, you should do so precisely.
 */
public class ToStringPractices {
    public static class ToStringWithFormat {
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
        }
    }

    public static class ToStringWithoutFormat {

        public static class PhoneNumber {
            private int hashcode = 0; // cache the hashcode only if object is Immutable
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

            /**
             * Returns a summary view of this phone number. There is no specific standard and it is subject to change.
             * However, it will be typically of format[areacode: 123, prefix: 91, lineNum: 5622]
             *
             * @return
             */
            @Override
            public String toString() {
                return String.format("[areacode: %d, prefix: %d, lineNum: %d]", this.areaCode, this.prefix, this.lineNum);
            }
        }
    }
}
