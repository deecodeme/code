package com.poc.code.practices.designPatterns.Creational.builder;

public class BankAccount {
    private int accountNumber;
    private String owner;
    private String branch;
    private double balance;
    private double interestRate;

    private BankAccount() {

    }

    public static class Builder {
        private int accountNumber;
        private String owner;
        private String branch;
        private double balance;
        private double interestRate;

        public Builder(int accountNumber) {
            this.accountNumber = accountNumber;
        }

        public Builder withOwner(String owner) {
            this.owner = owner;
            return this;
        }

        public Builder atBranch(String branch) {
            this.branch = branch;
            return this;
        }

        public Builder openingBalance(double balance) {
            this.balance = balance;
            return this;
        }

        public Builder atRate(double interestRate) {
            this.interestRate = interestRate;
            return this;
        }

        public BankAccount build() {
            BankAccount account1 = new BankAccount();
            account1.accountNumber = this.accountNumber;
            account1.balance = this.balance;
            account1.branch = branch;
            account1.owner = owner;
            account1.interestRate = interestRate;
            return account1;
        }
    }
}
