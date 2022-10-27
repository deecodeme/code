package com.poc.code.practices.effectiveJava.commonMethods;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EqualsViolationsTest {
    @Test
    public void equalsTransitivityViolationTest() {
        EqualsViolations.TransitivityViolation.ColouredPoint p1 = new EqualsViolations.TransitivityViolation.ColouredPoint(0, 0, EqualsViolations.TransitivityViolation.Colour.YELLOW);
        EqualsViolations.TransitivityViolation.Point p2 = new EqualsViolations.TransitivityViolation.Point(0, 0);
        EqualsViolations.TransitivityViolation.ColouredPoint p3 = new EqualsViolations.TransitivityViolation.ColouredPoint(0, 0, EqualsViolations.TransitivityViolation.Colour.GREEN);
        Assertions.assertTrue(p1.equalsTransitivityViolation(p2));
        Assertions.assertTrue(p2.equalsTransitivityViolation(p3));
        Assertions.assertFalse(p1.equalsTransitivityViolation(p3)); // As per the transitivity rules, p1 and p3 should be equal, but it is not
    }

    @Test
    public void equalsLiskovSubstitutionViolationTest() {
        EqualsViolations.TransitivityViolation.ColouredPoint p1 = new EqualsViolations.TransitivityViolation.ColouredPoint(0, 0, EqualsViolations.TransitivityViolation.Colour.YELLOW);
        EqualsViolations.TransitivityViolation.Point p2 = new EqualsViolations.TransitivityViolation.Point(0, 0);
        EqualsViolations.TransitivityViolation.ColouredPoint p3 = new EqualsViolations.TransitivityViolation.ColouredPoint(0, 0, EqualsViolations.TransitivityViolation.Colour.GREEN);
        Assertions.assertTrue(p1.equalsLiskovSubstitutionViolation(p2));
        Assertions.assertFalse(p2.equalsLiskovSubstitutionViolation(p3));
        Assertions.assertFalse(p1.equalsLiskovSubstitutionViolation(p3)); // As per the transitivity rules, p1 and p3 should be equal, but it is not
    }
}