package com.poc.code.practices.effectiveJava.commonMethods;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ClonePracticesTest {
    @Test
    public void phoneNumberClone() throws CloneNotSupportedException {
        ClonePractices.PhoneNumber phoneNumber = new ClonePractices.PhoneNumber(91, 99, 5434);
        ClonePractices.PhoneNumber clonedPhoneNumber = (ClonePractices.PhoneNumber) phoneNumber.clone();
        Assertions.assertTrue(clonedPhoneNumber != phoneNumber);
        Assertions.assertTrue(clonedPhoneNumber.getClass() == phoneNumber.getClass());
        Assertions.assertTrue(clonedPhoneNumber.equals(phoneNumber));
    }

    @Test
    public void cloneablePhoneNumberClone() throws CloneNotSupportedException {
        ClonePractices.CloneablePhoneNumber phoneNumber = new ClonePractices.CloneablePhoneNumber(91, 99, 5434);
        ClonePractices.CloneablePhoneNumber clonedPhoneNumber = (ClonePractices.CloneablePhoneNumber) phoneNumber.clone();
        Assertions.assertTrue(clonedPhoneNumber != phoneNumber);
        Assertions.assertTrue(clonedPhoneNumber.getClass() == phoneNumber.getClass());
        Assertions.assertTrue(clonedPhoneNumber.equals(phoneNumber));
    }
}