package com.poc.code.ps.strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationOfPhoneNumber {
    private static final Map<Character, String> letters = new HashMap<Character, String>(){{
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
    }};
    private List<String> combination = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0){
            return combination;
        }
        backtrack(0, new StringBuilder(), digits);
        return combination;
    }

    public void backtrack(int index, StringBuilder path, String digits){ // "23"
        if (path.length() == digits.length()){
            combination.add(path.toString());
            return;
        }

        for (char ch : letters.get(digits.charAt(index)).toCharArray()){
            path.append(ch);
            backtrack(index+1, path, digits);
            path.deleteCharAt(path.length()-1);
        }
    }
}
