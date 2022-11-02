package com.poc.code.practices.effectiveJava.commonMethods;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ToStringPracticesTest {
    @Test
    public void toStringWithStandard() {
        ToStringPractices.ToStringWithFormat.PhoneNumber phoneNumber = new ToStringPractices.ToStringWithFormat.PhoneNumber(123, 99, 9990);
        Assertions.assertEquals("123:099:9990", phoneNumber.toString());
    }

    @Test
    public void toStringWithoutStandard() {
        ToStringPractices.ToStringWithoutFormat.PhoneNumber phoneNumber = new ToStringPractices.ToStringWithoutFormat.PhoneNumber(123, 99, 9990);
        Assertions.assertEquals("[areacode: 123, prefix: 99, lineNum: 9990]", phoneNumber.toString());
    }
}