package com.poc.code.refactoring;

public class ReplaceTempWithQuery {
    public void method() {
        String str ="str";
        System.out.println(aString(str));
    }

    private String aString(String str) {
        return "string".concat(str);
    }
}
