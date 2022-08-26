package com.poc.code.ps.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
https://leetcode.com/problems/letter-case-permutation/

Given a string s, you can transform every letter individually to be lowercase or uppercase to create another string.
Return a list of all possible strings we could create. Return the output in any order.

Example 1:
Input: s = "a1b2"
Output: ["a1b2","a1B2","A1b2","A1B2"]
Example 2:

Input: s = "3z4"
Output: ["3z4","3Z4"]

 */
public class LetterCasePermutation {
    public List<String> letterCasePermutationTopDown(String s) {
        Map<Integer, List<String>> dp = new HashMap();
        return letterCasePermutationUtil(s, 0, dp);
    }

    public List<String> letterCasePermutationUtil(String s, int i, Map<Integer, List<String>> dp) {
        if (i == s.length()) {
            return List.of("");
        }
        if (dp.containsKey(i)) {
            return dp.get(i);
        }
        char ch = s.charAt(i);
        List<String> result = new ArrayList();
        List<String> nextList = letterCasePermutationUtil(s, i + 1, dp);
        String prefix = s.substring(i, i + 1);
        for (String str : nextList) {
            result.add(prefix + str);
        }
        if (!Character.isDigit(ch)) {
            if (isASmallCase(ch)) {
                prefix = String.valueOf(Character.toUpperCase(ch));
            } else {
                prefix = String.valueOf(Character.toLowerCase(ch));
            }
            for (String str : nextList) {
                result.add(prefix + str);
            }
        }
        dp.put(i, result);
        return dp.get(i);
    }

    public boolean isASmallCase(char ch) {
        int v = ch - 'a';
        if (v >= 0 && v <= 26) {
            return true;
        }
        return false;
    }

    public boolean isAUpperCase(char ch) {
        int v = ch - 'A';
        if (v >= 0 && v <= 26) {
            return true;
        }
        return false;
    }

    /*
    Intuition:
    f(i): All possible strings ending at index i
    f(i) = f(i-1)
     */

    /*
    Time Complexity: O(N)*O(2^N)
    Space Complexity:
     */
    public List<String> letterCasePermutationBottomUP(String s) {
        Map<Integer, List<String>> dp = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            List<String> res = new ArrayList<>();
            if (i == 0) {
                for (String prefix : getPrefix(s.charAt(i))) {
                    res.add(prefix);
                }
            } else {
                for (String prefix : getPrefix(s.charAt(i))) {
                    for (String str : dp.get(i - 1)) {
                        res.add(str + prefix);
                    }
                }
            }
            dp.put(i, res);
        }
        return dp.get(s.length() - 1);
    }

    public List<String> getPrefix(char ch) {
        List<String> pList = new ArrayList<>();
        pList.add(String.valueOf(ch));
        if (!Character.isDigit(ch)) {
            if (Character.isLowerCase(ch)) {
                pList.add(String.valueOf(Character.toUpperCase(ch)));
            } else {
                pList.add(String.valueOf(Character.toLowerCase(ch)));
            }
        }
        return pList;
    }
}
