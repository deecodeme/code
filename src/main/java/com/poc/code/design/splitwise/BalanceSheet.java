package com.poc.code.design.splitwise;

import java.util.List;

public interface BalanceSheet {
    List<Balance> getBalance();

    List<Balance> getBalance(User user);

    void addBalance(User paidBy, User paidFor, Float amount);

    void createBalanceSheet(User user);
}
