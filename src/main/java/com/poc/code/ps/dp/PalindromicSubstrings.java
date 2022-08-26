package com.poc.code.ps.dp;

/*
https://leetcode.com/problems/palindromic-substrings/
Given a string s, return the number of palindromic substrings in it.
A string is a palindrome when it reads the same backward as forward.
A substring is a contiguous sequence of characters within the string.

Example 1:
Input: s = "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
Example 2:

Input: s = "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 */
public class PalindromicSubstrings {

    /*
    Time Complexity: O(N^2)
    Space Complexity: O(N^2)
    */
    public int countSubstrings(String s) {
        int l = s.length();
        boolean[][] pal = new boolean[l][l];
        int count = 0;
        for (int i = 1; i <= l; i++) {
            for (int j = 0; j + i - 1 < l; j++) {
                int r = j + i - 1;
                if (i == 1) {
                    pal[j][r] = true;
                    count++;
                } else if (i == 2) {
                    if (s.charAt(j) == s.charAt(r)) {
                        pal[j][r] = true;
                        count++;
                    } else {
                        pal[j][r] = false;
                    }
                } else {
                    if (s.charAt(j) == s.charAt(r) && pal[j + 1][r - 1]) {
                        pal[j][r] = true;
                        count++;
                    } else {
                        pal[j][r] = false;
                    }
                }
            }
        }
        return count;
    }
}
