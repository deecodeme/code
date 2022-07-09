package com.poc.code.dp;

public class LongestPalindromicSubstring {

    public static int recursive(String s, int lowIndex, int highIndex) {
        if (lowIndex == highIndex) {
            return 1;
        }

        if (lowIndex > highIndex) {
            return 0;
        }

        if (s.charAt(lowIndex) == s.charAt(highIndex)) {
            int palindrome = recursive(s, lowIndex + 1, highIndex - 1);
            if (palindrome == highIndex - lowIndex - 1) {
                return 2 + palindrome;
            }
        }

        return Math.max(recursive(s, lowIndex, highIndex - 1),
                recursive(s, lowIndex + 1, highIndex));
    }

    public static int dpTabulation(String s) {
        int max = 1;
        if (s.length() == 0) {
            return 0;
        }

        boolean[][] DP = new boolean[s.length()][s.length()];

        for (int i = 0; i < s.length(); i++) {
            DP[i][i] = true;
        }

        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                max = 2;
                DP[i][i + 1] = true;
            } else {
                DP[i][i + 1] = false;
            }
        }

        for (int endIndex = 2; endIndex < s.length(); endIndex++) {
            for (int startIndex = 0; startIndex < endIndex-1; startIndex++) {
                if (s.charAt(startIndex) == s.charAt(endIndex) && DP[startIndex+1][endIndex-1]){
                    DP[startIndex][endIndex] = true;
                    int palindromeLength = 2 + endIndex-startIndex-1;
                    if (palindromeLength > max){
                        max = palindromeLength;
                    }
                }else{
                    DP[startIndex][endIndex] = false;
                }
            }
        }

        return max;
    }

    public static int palindromicSubstringCountDPTabulation(String s) {
        int count = 0;
        if (s.length() == 0) {
            return 0;
        }

        boolean[][] DP = new boolean[s.length()][s.length()];

        for (int i = 0; i < s.length(); i++) {
            count++;
            DP[i][i] = true;
        }

        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                count++;
                DP[i][i + 1] = true;
            } else {
                DP[i][i + 1] = false;
            }
        }

        for (int endIndex = 2; endIndex < s.length(); endIndex++) {
            for (int startIndex = 0; startIndex < endIndex-1; startIndex++) {
                if (s.charAt(startIndex) == s.charAt(endIndex) && DP[startIndex+1][endIndex-1]){
                    DP[startIndex][endIndex] = true;
                    count++;
                }else{
                    DP[startIndex][endIndex] = false;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        String str = "abdbca";
        System.out.println("longest palindromic substring: " + recursive(str, 0, str.length() - 1));
        System.out.println("longest palindromic substring dp tabulation: " + dpTabulation(str));
        System.out.println("count of palindromic substring dp tabulation: " + palindromicSubstringCountDPTabulation(str));

        str = "cdpdd";
        System.out.println("longest palindromic substring: " + recursive(str, 0, str.length() - 1));
        System.out.println("longest palindromic substring dp tabulation: " + dpTabulation(str));
        System.out.println("count of palindromic substring dp tabulation: " + palindromicSubstringCountDPTabulation(str));

        str = "pqr";
        System.out.println("longest palindromic substring: " + recursive(str, 0, str.length() - 1));
        System.out.println("longest palindromic substring dp tabulation: " + dpTabulation(str));
        System.out.println("count of palindromic substring dp tabulation: " + palindromicSubstringCountDPTabulation(str));

    }

}
