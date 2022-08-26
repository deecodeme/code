package com.poc.code.ps.dp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PalindromicSubstringsTest {

    @Test
    void countSubstrings() {
        PalindromicSubstrings obj = new PalindromicSubstrings();
        Assertions.assertEquals(3, obj.countSubstrings("abc"));
        Assertions.assertEquals(6, obj.countSubstrings("aaa"));
    }
}