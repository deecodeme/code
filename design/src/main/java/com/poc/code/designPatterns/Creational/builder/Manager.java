package com.poc.code.designPatterns.Creational.builder;

public class Manager {
    public static void main(String[] args) {
        BankAccount account1 = new BankAccount.
                Builder(12345).
                atBranch("branch").
                atRate(2.0).
                openingBalance(1000).
                withOwner("Ram").
                build();
    }
}
