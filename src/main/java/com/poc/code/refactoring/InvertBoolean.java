package com.poc.code.refactoring;

public class InvertBoolean {
    private double a;

    public boolean method() {
        if (a > 15 && a < 100) {
            a = 5;
            return true;
        }
        return false;
    }
}
