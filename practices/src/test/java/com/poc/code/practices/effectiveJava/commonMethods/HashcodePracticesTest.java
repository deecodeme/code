package com.poc.code.practices.effectiveJava.commonMethods;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class HashcodePracticesTest {
    @Test
    public void equalsWithoutHashcode() {
        Map<HashcodePractices.PhoneNumberWithoutHashcode, String> phoneDir = new HashMap<>();
        phoneDir.put(new HashcodePractices.PhoneNumberWithoutHashcode(101, 70, 5200), "Deepak");
        HashcodePractices.PhoneNumberWithoutHashcode deepakPhone = new HashcodePractices.PhoneNumberWithoutHashcode(101, 70, 5200);
        Assertions.assertFalse(phoneDir.containsKey(deepakPhone)); // This is not the expected behaviour.
    }

    @Test
    public void equalsWithHashcode() {
        Map<HashcodePractices.PhoneNumberWithHashcode, String> phoneDir = new HashMap<>();
        phoneDir.put(new HashcodePractices.PhoneNumberWithHashcode(101, 70, 5200), "Deepak");
        HashcodePractices.PhoneNumberWithHashcode deepakPhone = new HashcodePractices.PhoneNumberWithHashcode(101, 70, 5200);
        Assertions.assertTrue(phoneDir.containsKey(deepakPhone)); // This is the expected behaviour.
    }

    @Test
    public void hashcode() {
        HashcodePractices.PhoneNumberWithHashcode phoneA = new HashcodePractices.PhoneNumberWithHashcode(101, 70, 5200);
        HashcodePractices.PhoneNumberWithHashcode phoneB = new HashcodePractices.PhoneNumberWithHashcode(101, 70, 5200);
        Assertions.assertTrue(phoneA.equals(phoneB));
        Assertions.assertEquals(phoneA.hashCode(), phoneB.hashCode());
    }

}