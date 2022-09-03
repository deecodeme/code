package com.poc.code.ps.misc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NextSmallestPalindromeTest {

    @Test
    void nextSmallestPalindrome() {
        NextSmallestPalindrome obj = new NextSmallestPalindrome();
        Assertions.assertEquals(1221, obj.nextSmallestPalindrome(1124));
        Assertions.assertEquals(9, obj.nextSmallestPalindrome(8));
        Assertions.assertEquals(101, obj.nextSmallestPalindrome(99));
        Assertions.assertEquals(100001, obj.nextSmallestPalindrome(99999));
        Assertions.assertEquals(23632, obj.nextSmallestPalindrome(23545));
    }
}