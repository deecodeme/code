package com.poc.code.practices.effectiveJava.commonMethods;

/*
Equals method implements equivalence property. It has four properties.

1. Reflexive: x.equals(s) = true
2. Symmetric: x.equals(y) == y.equals(x)
3. Transitive: x.equals(y)=true && y.equals(z)=true then x.equals(z)=true
4. Consistent: x.equals(y) == true, should always be true
5. x.equals(null) = false


Gotcha -  There is no way to extend an instantiable class and add a value component while preserving the equals contract.
You will not have this problem when you extend an abstract class as abstract classes cannot be instantiated,
 and you can add the value components to the concrete class.

“There are some classes in the Java platform libraries that do extend an instantiable
         class and add a value component. For example, java.sql.Timestamp extends java.util.Date and adds a nanoseconds field.
          The equals implementation for Timestamp does violate symmetry and can cause erratic behavior if Timestamp and
           Date objects are used in the same collection or are otherwise intermixed.
            The Timestamp class has a disclaimer cautioning programmers against mixing dates and timestamps.
         While you won’t get into trouble as long as you keep them separate, there’s nothing
         to prevent you from mixing them, and the resulting errors can be hard to debug. This
         behavior of the Timestamp class was a mistake and should not be emulated.”
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

    public static class TransitivityViolation {
        public static class Point {
            private final int x;
            private final int y;

            public Point(int x, int y) {
                this.x = x;
                this.y = y;
            }

            @Override
            public boolean equals(Object obj) {
                if (!(obj instanceof Point)) return false;
                Point p = (Point) obj;
                return p.x == this.x && p.y == this.y;
            }

            /*
            This equal is just to have multiple representation of equals method to show the violations
             */
            public boolean equalsTransitivityViolation(Object obj) {
                if (!(obj instanceof Point)) return false;
                Point p = (Point) obj;
                return p.x == this.x && p.y == this.y;
            }

            /*
            This equal is just to have multiple representation of equals method to show the violations
             */
            public boolean equalsLiskovSubstitutionViolation(Object obj) {
                if (obj == null || obj.getClass() != getClass()) return false;
                Point p = (Point) obj;
                return p.x == this.x && p.y == this.y;
            }

        }

        public static class ColouredPoint extends Point {
            private Colour colour;

            public ColouredPoint(int x, int y, Colour colour) {
                super(x, y);
                this.colour = colour;
            }

            /*
            Not overriding equals(), inherits from super class, hence ignoring colour. Which does not violate
             transitivity but not a correct logical implementation

             There is no way to extend an instantiable class and add a value component while preserving the equals contract.
             One alternative way to compare the objects of same implementation by doing getClass, but that violates liskov-substitution principle.
             */
            @Override
            public boolean equals(Object obj) {
                if (obj == null) return false;
                if (obj instanceof ColouredPoint)
                    return ((ColouredPoint) obj).colour == this.colour && super.equals(obj);
                return super.equals(obj);
            }

            /*
            This violates Transitivity as well as Symmetry
             */
            public boolean equalsSymmetryViolation(Object obj) {
                if (!(obj instanceof ColouredPoint))
                    return false;
                return ((ColouredPoint) obj).colour == this.colour && super.equals(obj);
            }

            @Override
            public boolean equalsTransitivityViolation(Object obj) {
                if (!(obj instanceof Point)) return false;
                if (obj instanceof ColouredPoint)
                    return ((ColouredPoint) obj).colour == this.colour && super.equals(obj);
                return super.equals(obj);
            }

            /*
            Liskov Substitution Principle - Any important property of type should also hold for all its subclasses,
             so that any method written for the type should equally work for its subtypes.

             In this case the super.equals() would not work the same and hence the violation
             */
            @Override
            public boolean equalsLiskovSubstitutionViolation(Object obj) {
                if (!(obj instanceof Point)) return false;
                if (obj instanceof ColouredPoint)
                    return ((ColouredPoint) obj).colour == this.colour && super.equals(obj);
                return super.equals(obj);
            }
        }

        public static class PointWithEqualsWorkaround {
            // prefer composition to inheritance
            private final Point point;
            private final Colour colour;

            public PointWithEqualsWorkaround(int x, int y, Colour colour) {
                this.point = new Point(x, y);
                this.colour = colour;
            }

            public Point asPoint() {
                return this.point;
            }

            @Override
            public boolean equals(Object obj) {
                if (!(obj instanceof PointWithEqualsWorkaround)) return false;
                PointWithEqualsWorkaround p = (PointWithEqualsWorkaround) obj;
                return point.equals(p.asPoint()) && p.colour == colour;
            }
        }

        public static enum Colour {
            RED,
            GREEN,
            YELLOW
        }

    }


}


