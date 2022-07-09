package com.poc.code.educative;

import java.util.HashMap;
import java.util.Map;

public class LongestNonRepeatingString {
    /*
    Input: String="aabccbb"
    Output: 3
    Explanation: The longest substring without any repeating characters is "abc".
 */

    /*
    Input: String="abccde"
    Output: 3
    Explanation: Longest substrings without any repeating characters are "abc" & "cde".
     */

    /*
    Time Complexity: O(N)
    Space Complexity: O(1)
     */
    public static int lisTypeSolution(String str) {
        if (str.length() == 0) {
            return 0;
        }
        int maxCount = 1;
        int count = 1;
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) != str.charAt(i - 1)) {
                count++;
                if (count > maxCount) {
                    maxCount = count;
                }
            } else {
                count = 1;
            }
        }
        return maxCount;
    }

    /*
    abcdecfgc
    aaaabcd
     */
    public static int slidingWindowSolution(String str) {
        Map<Character, Integer> lookup = new HashMap<>();
        if (str.length() == 0) {
            return 0;
        }
        int maxCount = 1;
        int startIndex = 0;
        int endIndex = 0;
        lookup.put(Character.valueOf(str.charAt(endIndex)), endIndex);
        endIndex++;

        while (endIndex < str.length()) {
            if (!lookup.containsKey(str.charAt(endIndex))) {
                lookup.put(Character.valueOf(str.charAt(endIndex)), endIndex);
                if ((endIndex - startIndex +1) > maxCount) {
                    maxCount = endIndex - startIndex + 1;
                }
            } else {
                int lastIndex = lookup.get(str.charAt(endIndex));
                startIndex = lastIndex+1;
                lookup.put(Character.valueOf(str.charAt(endIndex)), lastIndex);
            }
            endIndex++;
        }
        return maxCount;
    }

    public static void main(String[] args) {
        String str = "aabccbb";
        System.out.println("Longest substrings without any repeating characters: " + lisTypeSolution(str));
        System.out.println("Longest substrings without any repeating characters, sliding approach: " + slidingWindowSolution(str));

        str = "abbbb";
        System.out.println("Longest substrings without any repeating characters: " + lisTypeSolution(str));
        System.out.println("Longest substrings without any repeating characters, sliding approach: " + slidingWindowSolution(str));


        str = "abccde";
        System.out.println("Longest substrings without any repeating characters: " + lisTypeSolution(str));
        System.out.println("Longest substrings without any repeating characters, sliding approach: " + slidingWindowSolution(str));

        str = "abcdecfguvwxyzc";
        System.out.println("Longest substrings without any repeating characters: " + lisTypeSolution(str));
        System.out.println("Longest substrings without any repeating characters, sliding approach: " + slidingWindowSolution(str));
    }
}
