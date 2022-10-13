package com.poc.code.practices.commonMethods;

/*
Equals method implements equivalence property. It has four properties.

1. Reflexive: x.equals(s) = true
2. Symmetric: x.equals(y) == y.equals(x)
3. Transitive: x.equals(y)=true && y.equals(z)=true then x.equals(z)=true
4. Consistent: x.equals(y) == true, should always be true

 */
public class EqualsViolations {
    /*
    There are cases where you do not need equals method and hence you would not override it.
    Make sure to throw an Error, if invoked, so not to have unintended behaviour.
     */
    public static class NoEquals {
        @Override
        public boolean equals(Object obj) {
            throw new AssertionError("Equals should not be called");
        }
    }

    public static class SymmetryViolation {
        public static class CaseInsensitiveString {
            private String s;

            public CaseInsensitiveString(String s) {
                this.s = s;
            }

            /*
               String s1 = new CaseInsensitiveString("ABC");
               String s2 = "abc"
               s1.equals(s2) = true but s2.equals(s1) is not true
            */
            @Override
            public boolean equals(Object obj) {
                if (obj == null) {
                    return false;
                }
                if (obj instanceof CaseInsensitiveString) {
                    return s.equalsIgnoreCase(((CaseInsensitiveString) obj).s);
                }
                if (obj instanceof String) {
                    return s.equalsIgnoreCase((String) obj);
                }
                return false;
            }
        }
    }



}


