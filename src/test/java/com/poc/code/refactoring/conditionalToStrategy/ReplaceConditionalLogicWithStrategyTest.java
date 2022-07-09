package com.poc.code.refactoring.conditionalToStrategy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReplaceConditionalLogicWithStrategyTest {
    private ReplaceConditionalLogicWithStrategy instance;

    @BeforeEach
    public void setUp() throws Exception {
        instance = new ReplaceConditionalLogicWithStrategy();
    }

    @Test
    public void calculateInsuranceLowSegment() {
        Assertions.assertEquals(1825, instance.calculateInsurance(5000), 0.01);
    }

    @Test
    public void calculateInsuranceMediumSegment() {
        Assertions.assertEquals(38600, instance.calculateInsurance(25000), 0.01);
    }

    @Test
    public void calculateInsuranceHighSegment() {
        Assertions.assertEquals(78500, instance.calculateInsurance(50000), 0.01);
    }

    @Test
    public void calculateInsuranceVeryHighSegment() {
        Assertions.assertEquals(106400, instance.calculateInsurance(100000), 0.01);
    }
}