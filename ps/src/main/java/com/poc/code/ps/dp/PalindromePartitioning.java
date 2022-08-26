package com.poc.code.ps.dp;

/*
https://leetcode.com/problems/palindrome-partitioning/

Given a string s, partition s such that every substring of the partition is a palindrome.
Return all possible palindrome partitioning of s.A palindrome string is a string that reads the same backward as forward.

Example 1:
Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]
Example 2:

Input: s = "a"
Output: [["a"]]
 */
public class PalindromePartitioning {
//    /*
//    f(i) : list of palindromes from index i
//    */
//    public List<List<String>> partition(String s) {
//
//    }
//
//    public List<List<String>> partitionRecursion(String s, int l, int h) {
//        if (l == h) {
//            return List.of(List.of(s.substring(l, h + 1)));
//        }
//
//        for (int i = l; i < h; i++) {
//            List<List<String>> left = partitionRecursion(s, l, i);
//            List<List<String>> right = partitionRecursion(s, l + 1, h);
//            if (left.size() > 0 && right.size() > 0) {
//
//            }
//        }
//
//    }
}
