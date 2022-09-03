package com.poc.code.ps.misc;

import java.util.ArrayDeque;
import java.util.Deque;

/*
Write a function that takes an integer as an input and return the next larger smallest number that is a palindrome.
[0:8] -> next number
10 - 98 -> 34 -> 44
99 -> 1001
100 - 200 ->
102 ->    111
105 ->
201 -> 202
203 ->


11 22 33 44 55  99
101 111 121 131  191
202 212 222 232  292
303              393
404              494
                 898
909              999
1001 1111        1991

4004  4114 4224 4334           4994

4082 -> 4114
4323 -> 4334

 */
public class NextSmallestPalindrome {
    public int nextSmallestPalindrome(int n) {
        Deque<Integer> stack = new ArrayDeque<>();
        boolean allNine = true;
        while (n != 0) {
            int d = n % 10;
            if (d != 9) {
                allNine = false;
            }
            stack.push(d);
            n /= 10;
        }
        int[] digits = new int[stack.size()];
        if (allNine) {
            digits = new int[digits.length + 1];
            digits[0] = 1;
            digits[digits.length - 1] = 1;
            return digitArrayToInt(digits);
        }
        int i = 0;
        while (!stack.isEmpty()) {
            digits[i++] = stack.pop();
        }
        int j;
        if (digits.length % 2 == 0) {
            i = digits.length / 2 - 1;
            j = digits.length / 2;
        } else {
            i = digits.length / 2;
            j = digits.length / 2;
        }
        boolean incremented = false;
        while (i >= 0 && j < digits.length) {
            if (digits[i] != 9 && digits[i] <= digits[j] && !incremented) {
                digits[i]++;
                incremented = true;
            }
            digits[j] = digits[i];
            i--;
            j++;
        }
        return digitArrayToInt(digits);
    }

    /*
    [1, 2, 3]
    3 + 20 * 100
     */
    private int digitArrayToInt(int[] digits) {
        int n = 0;
        int i = 0;
        while (i < digits.length) {
            n += digits[i] * Math.pow(10, digits.length - i - 1);
            i++;
        }
        return n;
    }
}
