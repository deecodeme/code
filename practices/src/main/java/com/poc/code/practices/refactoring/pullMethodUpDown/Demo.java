package com.poc.code.practices.refactoring.pullMethodUpDown;

public class Demo {
    public static void main(String[] args) {
        SubClassB subClassB = new SubClassB();
        subClassB.method();

        SubClassA subClassA = new SubClassA();
        subClassA.method();
    }
}
