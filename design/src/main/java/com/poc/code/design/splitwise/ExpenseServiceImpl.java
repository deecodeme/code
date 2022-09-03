package com.poc.code.design.splitwise;

import java.util.List;
import java.util.Map;

public class ExpenseServiceImpl implements ExpenseService {
    private BalanceSheet balanceSheet;

    public ExpenseServiceImpl(BalanceSheet balanceSheet) {
        this.balanceSheet = balanceSheet;
    }

    @Override
    public void registerEqual(Float amount, User paidBy, List<User> paidFor) {
        int usersCount = paidFor.size();
        float dividedAmount = amount / usersCount;
        float firstUserAmount = amount - dividedAmount * (usersCount - 1);
        boolean firstUser = true;
        for (int i = 0; i < paidFor.size(); i++) {
            if (paidFor.get(i) == paidBy) {
                continue;
            }
            if (firstUser) {
                this.balanceSheet.addBalance(paidBy, paidFor.get(i), firstUserAmount);
                firstUser = false;
            } else {
                this.balanceSheet.addBalance(paidBy, paidFor.get(i), dividedAmount);
            }
        }
    }

    @Override
    public void registerExact(Float amount, User paidBy, Map<User, Float> paidFor) {
        paidFor.forEach((k, v) -> {
            if (k != paidBy) {
                this.balanceSheet.addBalance(paidBy, k, v);
            }
        });
    }

    @Override
    public void registerPercent(Float amount, User paidBy, Map<User, Float> paidFor) {
        final int[] temp = {0};
        paidFor.forEach((k, v) -> temp[0] += v);
        if (temp[0] != 100) {
            System.out.println("Percentage breakup do not add up to 100");
        }
        paidFor.forEach((k, v) -> {
            if (k != paidBy) {
                this.balanceSheet.addBalance(paidBy, k, (amount * v) / 100);
            }
        });
    }
}
