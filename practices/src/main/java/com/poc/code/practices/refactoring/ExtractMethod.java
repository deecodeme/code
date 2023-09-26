package com.poc.code.practices.refactoring;

public class ExtractMethod {
    private String name;

    void printOwing() {
        //printBanner();

        // Print details.
        printDetails();
    }

    private void printDetails() {
        System.out.println("name: " + name);
        System.out.println("amount: " + getOutstanding());
    }

    private String getOutstanding() {
        return null;
    }
}
